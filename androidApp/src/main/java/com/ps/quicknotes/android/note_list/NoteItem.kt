package com.ps.quicknotes.android.note_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ps.quicknotes.domain.time.DateTimeUtil
import com.ps.quicknotes.note.Note

@Composable
fun NoteItem(
    note: Note,
    onNoteClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formattedDate = remember(note.created) {
        DateTimeUtil.formatNoteDate(note.created)
    }
    Column(modifier = modifier
        .clickable { onNoteClick() }
        .padding(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(Modifier.fillMaxWidth(0.9f)) {
                Text(
                    text = note.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = com.ps.quicknotes.android.R.string.delete_note),
                modifier = Modifier.clickable(MutableInteractionSource(), null) {
                    onDeleteClick()
                })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = note.content, fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = formattedDate, color = MaterialTheme.colors.secondaryVariant, modifier = Modifier.align(Alignment.End)
        )
    }
}