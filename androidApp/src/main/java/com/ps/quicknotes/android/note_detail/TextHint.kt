package com.ps.quicknotes.android.note_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ps.quicknotes.presentation.Colors

@Composable
fun TextHint(
    text: String,
    hint: String,
    isHintVisible: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChanged: (FocusState) -> Unit
) {
    Box(modifier = modifier) {
        BasicTextField(value = text,
            onValueChange = onValueChanged,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused -> onFocusChanged(isFocused) })
        if (isHintVisible) {
            Text(text = hint, style = textStyle, color = com.ps.quicknotes.android.core.theme.LightBlue)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}