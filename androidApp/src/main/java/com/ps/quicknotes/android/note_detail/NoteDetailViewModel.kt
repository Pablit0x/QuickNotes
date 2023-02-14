package com.ps.quicknotes.android.note_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ps.quicknotes.domain.time.DateTimeUtil
import com.ps.quicknotes.note.Note
import com.ps.quicknotes.note.NoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource, private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val noteTitle = savedStateHandle.getStateFlow("noteTitle", "")
    private val isNoteTitleFocused = savedStateHandle.getStateFlow("isNoteTitleFocused", false)
    private val noteContent = savedStateHandle.getStateFlow("noteContent", "")
    private val isNoteContentFocused = savedStateHandle.getStateFlow("isNoteContentFocused", false)
    private val noteColor = savedStateHandle.getStateFlow("noteColor", Note.getRandomColor())

    val state = combine(
        noteTitle,
        isNoteTitleFocused,
        noteContent,
        isNoteContentFocused,
        noteColor
    ) { title, isTitleFocused, content, isContentFocused, color ->
        NoteDetailState(
            noteTitle = title,
            isNoteTitleHintDisplayed = title.isEmpty(),
            noteContent = content,
            isNoteContentHintDisplayed = content.isEmpty(),
            noteColor = color
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteDetailState())

    private val _hasNoteBeenSaved = MutableStateFlow(false)
    val hasNoteBeenSaved = _hasNoteBeenSaved.asStateFlow()

    private val _hasColorBeenChanged = MutableStateFlow(false)
    val hasColorBeenChanged = _hasColorBeenChanged.asStateFlow()

    private var existingNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("noteId")?.let { existingNoteId ->
            if (existingNoteId == -1L) {
                return@let
            }
            this.existingNoteId = existingNoteId
            viewModelScope.launch {
                noteDataSource.getNoteById(existingNoteId)?.let { note ->
                    savedStateHandle["noteTitle"] = note.title
                    savedStateHandle["noteContent"] = note.content
                    savedStateHandle["noteColor"] = note.colorHex
                }
            }
        }
    }

    fun onNoteTitleChanged(updatedTitle: String) {
        savedStateHandle["noteTitle"] = updatedTitle
    }

    fun onNoteContentChanged(updatedContent: String) {
        savedStateHandle["noteContent"] = updatedContent
    }

    fun onNoteTitleFocusChanged(isFocused: Boolean) {
        savedStateHandle["isNoteTitleFocused"] = isFocused
    }

    fun onNoteContentFocusChanged(isFocused: Boolean) {
        savedStateHandle["isNoteContentFocused"] = isFocused
    }

    fun onNoteColorChange(color : Long){
        savedStateHandle["noteColor"] = color
        _hasColorBeenChanged.value = true
    }

    fun saveNote() {
        viewModelScope.launch {
            noteDataSource.insertNote(
                Note(
                    id = existingNoteId,
                    title = noteTitle.value,
                    content = noteContent.value,
                    colorHex = noteColor.value,
                    created = DateTimeUtil.now()
                )
            )

            _hasNoteBeenSaved.value = true
        }
    }

    fun isUpdatedNotedNotSaved(title: String, content: String, color: Long) : Boolean {
        var result = false
        savedStateHandle.get<Long>("noteId")?.let { existingNoteId ->
            if (existingNoteId == -1L) {
                if (title.isNotEmpty() || content.isNotEmpty() || _hasColorBeenChanged.value) {
                    return true
                }
            }
            this.existingNoteId = existingNoteId
            viewModelScope.launch {
                noteDataSource.getNoteById(existingNoteId)?.let { note ->
                    if (title != note.title || content != note.content || color != note.colorHex) {
                        result = true
                        return@launch
                    }
                }
            }
        }
        return result
    }
}