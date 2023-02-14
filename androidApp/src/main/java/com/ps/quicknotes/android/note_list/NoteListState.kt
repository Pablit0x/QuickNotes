package com.ps.quicknotes.android.note_list

import com.ps.quicknotes.note.Note


data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)
