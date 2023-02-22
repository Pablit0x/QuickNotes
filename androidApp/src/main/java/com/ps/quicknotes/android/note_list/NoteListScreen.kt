package com.ps.quicknotes.android.note_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ps.quicknotes.android.R
import com.ps.quicknotes.android.core.navigation.Routes
import com.ps.quicknotes.android.core.presentation.ForestGreenHex

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(
    navController: NavController, viewModel: NoteListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.loadNotes()
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                navController.navigate( Routes.NoteDetail + "/-1L")
            },
            backgroundColor = MaterialTheme.colors.onBackground,
            contentColor = MaterialTheme.colors.background
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_note)
            )
        }
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                SearchTextField(
                    text = state.searchText,
                    isSearchActive = state.isSearchActive,
                    onTextChange = viewModel::onSearchTextChange,
                    onSearchClick = viewModel::onToggleSearch,
                    onCloseClick = viewModel::onToggleSearch,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                )
                this@Column.AnimatedVisibility(
                    visible = !state.isSearchActive, enter = fadeIn(), exit = fadeOut()
                ) {
                    Text(
                        text = stringResource(id = R.string.all_notes), fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Medium, fontSize = 30.sp
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {

                items(items = state.notes.sortedByDescending { it.created }, key = { it.id!! }) { note ->
                    NoteItem(
                        note = note,
                        onNoteClick = {
                            navController.navigate(Routes.NoteDetail + "/${note.id}")
                        },
                        onDeleteClick = {
                            viewModel.deleteNoteById(note.id!!)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .animateItemPlacement()
                            .clip(shape = RoundedCornerShape(25.dp))
                            .border(
                                width = 3.dp,
                                color = Color(note.colorHex),
                                shape = RoundedCornerShape(25.dp)
                            )
                            .gradientSurface()
                    )
                }
            }
        }
    }
}