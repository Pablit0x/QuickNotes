package com.ps.quicknotes.android.note_detail

data class NoteDetailState(
    val noteTitle: String = "",
    val isNoteTitleHintDisplayed: Boolean = false,
    val noteContent: String = "",
    val isNoteContentHintDisplayed: Boolean = false,
    var noteColor : Long = 0xFFFFFFF
)
