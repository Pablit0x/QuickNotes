package com.ps.quicknotes.data.note

import com.ps.quicknotes.database.QuickNotesDb
import com.ps.quicknotes.domain.time.DateTimeUtil
import com.ps.quicknotes.note.Note
import com.ps.quicknotes.note.NoteDataSource

class SqlDelightNoteDataSource(
    db: QuickNotesDb
) : NoteDataSource {

    private val queries = db.noteQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            created = DateTimeUtil.toEpochMillis(note.created)
        )
    }

    override suspend fun getNoteById(noteId: Long): Note? {
        return queries
            .getNoteById(id = noteId)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map {
                it.toNote()
            }
    }

    override suspend fun deleteNoteById(noteId: Long) {
        queries.deleteNote(id = noteId)
    }
}