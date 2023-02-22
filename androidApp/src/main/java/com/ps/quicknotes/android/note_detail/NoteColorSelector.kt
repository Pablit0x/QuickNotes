package com.ps.quicknotes.android.note_detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ps.quicknotes.android.core.presentation.*

@ExperimentalMaterialApi
@Composable
fun NoteColorSelector(
    cardColor: Color, onColorChange: (Color) -> Unit
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
        ) {
            if (!expandedState) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .border(width = 1.dp, color = MaterialTheme.colors.onBackground, shape = CircleShape)
                        .background(cardColor, shape = CircleShape)
                )
            }
            if (expandedState) {
                Column{
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(48.dp)
                        .background(ForestGreenHex, shape = CircleShape)
                        .clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            onColorChange(ForestGreenHex)
                            expandedState = !expandedState
                        })
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(48.dp)
                        .background(DarkBlueHex, shape = CircleShape)
                        .clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            onColorChange(DarkBlueHex)
                            expandedState = !expandedState
                        })
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(48.dp)
                        .background(OrangeHex, shape = CircleShape)
                        .clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            onColorChange(OrangeHex)
                            expandedState = !expandedState
                        })
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(48.dp)
                        .background(PinkRedHex, shape = CircleShape)
                        .clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            onColorChange(PinkRedHex)
                            expandedState = !expandedState
                        })
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(48.dp)
                        .background(PurpleHex, shape = CircleShape)
                        .clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            onColorChange(PurpleHex)
                            expandedState = !expandedState
                        })
                    IconButton(onClick = { expandedState = !expandedState }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                                .padding(4.dp)
                                .size(48.dp)
                        )
                    }
                }
            }
        }
    }
}