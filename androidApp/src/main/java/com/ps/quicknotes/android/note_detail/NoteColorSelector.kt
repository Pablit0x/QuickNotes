package com.ps.quicknotes.android.note_detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ps.quicknotes.android.core.theme.*

@ExperimentalMaterialApi
@Composable
fun NoteColorSelector(
    cardColor: Color, onColorChange: (Color) -> Unit, padding: Dp = 12.dp
) {
    var expandedState by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }


    Box(modifier = Modifier
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 300, easing = LinearOutSlowInEasing
            )
        )
        .clickable(
            interactionSource = interactionSource, indication = null
        ) {
            expandedState = !expandedState
        }) {
        Column(
            modifier = Modifier.padding(padding)
        ) {
            if (!expandedState) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(cardColor, shape = CircleShape)
                )
            }
            if (expandedState) {
                Row {
                    IconButton(onClick = { expandedState = !expandedState }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                                .padding(top = 8.dp, end = 8.dp)
                                .size(48.dp)
                        )
                    }
                    Box(modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp)
                        .size(48.dp)
                        .background(YellowHex, shape = CircleShape)
                        .clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            onColorChange(YellowHex)
                            expandedState = !expandedState
                        })
                    Box(modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp)
                        .size(48.dp)
                        .background(PeachHex, shape = CircleShape)
                        .clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            onColorChange(PeachHex)
                            expandedState = !expandedState
                        })
                    Box(modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp)
                        .size(48.dp)
                        .background(OrangeHex, shape = CircleShape)
                        .clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            onColorChange(OrangeHex)
                            expandedState = !expandedState
                        })
                    Box(modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp)
                        .size(48.dp)
                        .background(PinkRedHex, shape = CircleShape)
                        .clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            onColorChange(PinkRedHex)
                            expandedState = !expandedState
                        })
                    Box(modifier = Modifier
                        .padding(top = 8.dp)
                        .size(48.dp)
                        .background(PurpleHex, shape = CircleShape)
                        .clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            onColorChange(PurpleHex)
                            expandedState = !expandedState
                        })
                }
            }
        }
    }
}