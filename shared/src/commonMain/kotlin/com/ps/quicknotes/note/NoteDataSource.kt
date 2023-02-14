package com.ps.quicknotes.note

interface NoteDataSource {
    suspend fun insertNote(note: Note)
    suspend fun getNoteById(noteId: Long): Note?
    suspend fun getAllNotes() : List<Note>
    suspend fun deleteNoteById(noteId: Long)
}