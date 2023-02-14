package com.ps.quicknotes.data.note

import com.ps.quicknotes.domain.time.DateTimeUtil
import com.ps.quicknotes.note.Note

class SearchNotes {
    fun execute(notes: List<Note>, query: String): List<Note> {
        if (query.isBlank()) {
            return notes
        }
        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) || it.content.trim().lowercase()
                .contains(query.lowercase())
        }.sortedBy {
            DateTimeUtil.toEpochMillis(it.created)
        }
    }
}