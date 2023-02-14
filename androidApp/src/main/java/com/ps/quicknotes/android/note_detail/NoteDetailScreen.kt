package com.ps.quicknotes.android.note_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ps.quicknotes.android.note_list.gradientSurface

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteDetailScreen(
    noteId: Long, viewModel: NoteDetailViewModel = hiltViewModel(), navController: NavController
) {
    val state by viewModel.state.collectAsState()
    val hasNoteBeenSaved by viewModel.hasNoteBeenSaved.collectAsState()
    var noteColor = state.noteColor
    var showDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = hasNoteBeenSaved) {
        if (hasNoteBeenSaved) {
            navController.popBackStack()
        }
    }
    var topBarTitle by remember { mutableStateOf("") }

    topBarTitle = if (noteId == -1L) {
        stringResource(id = com.ps.quicknotes.android.R.string.create_note)
    } else {
        stringResource(id = com.ps.quicknotes.android.R.string.edit_note)
    }

    Scaffold(

        topBar = {
            TopAppBar(title = {
                Text(text = topBarTitle)
            },
                navigationIcon = {
                    IconButton(onClick = {
                        if (viewModel.isUpdatedNotedNotSaved(
                                title = state.noteTitle,
                                content = state.noteContent,
                                color = state.noteColor
                            )
                        ) {
                            showDialog = true
                        } else {
                            navController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Navigate Back"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                actions = {
                    AnimatedVisibility(visible = state.noteTitle.isNotEmpty() || state.noteContent.isNotEmpty()) {
                        IconButton(onClick = viewModel::saveNote) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = stringResource(id = com.ps.quicknotes.android.R.string.save_note),
                                tint = MaterialTheme.colors.onSurface
                            )
                        }
                    }
                })
        }) { padding ->
        Column(
            modifier = Modifier
                .gradientSurface()
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TextHint(
                text = state.noteTitle,
                hint = stringResource(id = com.ps.quicknotes.android.R.string.title),
                isHintVisible = state.isNoteTitleHintDisplayed,
                onValueChanged = {
                    if (it.length <= 200) {
                        viewModel.onNoteTitleChanged(it)
                    }
                },
                onFocusChanged = {
                    viewModel.onNoteTitleFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                ),
                modifier = Modifier
                    .border(
                        width = 3.dp, color = Color(noteColor), shape = RoundedCornerShape(25.dp)
                    )
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextHint(
                text = state.noteContent,
                hint = stringResource(id = com.ps.quicknotes.android.R.string.start_typing),
                isHintVisible = state.isNoteContentHintDisplayed,
                onValueChanged = viewModel::onNoteContentChanged,
                onFocusChanged = {
                    viewModel.onNoteContentFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 20.sp, color = MaterialTheme.colors.onSurface),
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            )

            NoteColorSelector(cardColor = Color(state.noteColor), onColorChange = {
                noteColor = it.toArgb().toLong()
                viewModel.onNoteColorChange(noteColor)
            })
            if (showDialog) {
                UnsavedChangesDialog(
                    closeDialog = { showDialog = false },
                    saveChanges = { viewModel.saveNote() },
                    navController = navController
                )
            }
        }

    }
}