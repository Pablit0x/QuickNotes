package com.ps.quicknotes.data.local

import android.content.Context
import com.ps.quicknotes.database.QuickNotesDb
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            schema = QuickNotesDb.Schema, context = context, name = "note.db"
        )
    }
}