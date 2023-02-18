package com.ps.quicknotes.android.di

import android.app.Application
import com.ps.quicknotes.data.local.DatabaseDriverFactory
import com.ps.quicknotes.data.note.SqlDelightNoteDataSource
import com.ps.quicknotes.database.QuickNotesDb
import com.ps.quicknotes.note.NoteDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application) : SqlDriver{
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver) : NoteDataSource {
        return SqlDelightNoteDataSource(QuickNotesDb(driver = driver))
    }
}