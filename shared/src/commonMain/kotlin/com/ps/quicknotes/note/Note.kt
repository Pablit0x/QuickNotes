package com.ps.quicknotes.note

import com.ps.quicknotes.presentation.*
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: LocalDateTime
) {
    companion object {
        private val colors = listOf(Colors.YellowHex, Colors.PeachHex, Colors.OrangeHex, Colors.PinkRedHex, Colors.PurpleHex)

        fun getRandomColor() = colors.random()
    }

}
