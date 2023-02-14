package com.ps.quicknotes.data.local

import com.ps.quicknotes.database.QuickNotesDb
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {

    actual fun create(): SqlDriver {
        return NativeSqliteDriver(schema = QuickNotesDb.Schema, name = "note.db")
    }
}