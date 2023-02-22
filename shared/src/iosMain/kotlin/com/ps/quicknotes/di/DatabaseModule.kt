package com.ps.quicknotes.di

import com.ps.quicknotes.data.local.DatabaseDriverFactory
import com.ps.quicknotes.data.note.SqlDelightNoteDataSource
import com.ps.quicknotes.database.QuickNotesDb

class DatabaseModule {
    private val factory by lazy{DatabaseDriverFactory()}

    val noteDataSource by lazy {
        SqlDelightNoteDataSource(QuickNotesDb(factory.create()))
    }
}