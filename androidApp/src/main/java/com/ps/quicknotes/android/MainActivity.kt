package com.ps.quicknotes.android


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ps.quicknotes.android.core.presentation.Routes
import com.ps.quicknotes.android.note_detail.NoteDetailScreen
import com.ps.quicknotes.android.note_list.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                NotesRoot()
            }
        }
    }

    @Composable
    fun NotesRoot() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.NoteList) {
            composable(route = Routes.NoteList) {
                NoteListScreen(navController)
            }
            composable(route = Routes.NoteDetail + "/{noteId}",
                arguments = listOf(navArgument("noteId") {
                    type = NavType.LongType
                    defaultValue = -1L
                })) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getLong("noteId") ?: -1L
                NoteDetailScreen(noteId = noteId, navController = navController)
            }
        }
    }
}
