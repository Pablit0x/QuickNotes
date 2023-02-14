package com.ps.quicknotes.android.note_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ps.quicknotes.android.R
import com.ps.quicknotes.android.note_list.gradientSurface

@Composable
fun UnsavedChangesDialog(
    closeDialog: () -> Unit, saveChanges: () -> Unit, navController: NavController
) {
    AlertDialog(onDismissRequest = closeDialog, title = {
        Text(
            text = stringResource(id = R.string.unsaved_changes)
        )
    }, text = {
        Column {
            Text(
                text = stringResource(id = R.string.unsaved_changes_description), fontSize = 16.sp
            )
        }
    }, confirmButton = {
        TextButton(onClick = {
            saveChanges()
            closeDialog()
        }) {
            Text(
                text = stringResource(id = R.string.save_changes), color = MaterialTheme.colors.onSurface
            )
        }
    }, dismissButton = {
        TextButton(onClick = {
            closeDialog()
            navController.popBackStack()
        }) {
            Text(
                text = stringResource(id = R.string.discard_changes), color = MaterialTheme.colors.onSurface
            )
        }
    },

        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .gradientSurface()
    )
}