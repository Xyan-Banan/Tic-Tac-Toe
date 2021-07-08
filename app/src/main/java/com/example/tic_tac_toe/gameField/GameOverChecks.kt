package com.example.tic_tac_toe.gameField

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.example.tic_tac_toe.R
import com.example.tic_tac_toe.startMenu.GameType

//TODO("change to sets")
private fun findGameOverRow(buttons: List<ImageButton>): Drawable? {
    val images = buttons.map { it.drawable }
    if (images[0] != null && (
                images[0] == images[1] && images[1] == images[2] || //top line
                        images[0] == images[3] && images[3] == images[6] || //left line
                        images[0] == images[4] && images[4] == images[8]    //left diagonal
                )
    ) {
        return images[0]
    } else if (
        images[4] != null && (
                images[3] == images[4] && images[4] == images[5] || //mid line
                        images[1] == images[4] && images[4] == images[7] || //center line
                        images[2] == images[4] && images[4] == images[6]    //right diagonal
                )
    ) {
        return images[4]
    } else if (
        images[8] != null && (
                images[6] == images[7] && images[7] == images[8] || //bottom line
                        images[2] == images[5] && images[5] == images[8]    //right line
                )
    ) {
        return images[8]
    }
    return null
}

@SuppressLint("UseCompatLoadingForDrawables")
fun isGameOver(
    buttons: List<ImageButton>,
    playerDrawable: Drawable,
    gameType: GameType,
    buttonsLeft: Int,
    applicationContext: Context
): Boolean {
    findGameOverRow(buttons)?.also { buttonImg ->
        val crossBitmap = AppCompatResources.getDrawable(applicationContext, R.drawable.cross)?.toBitmap()

        val msg = when (gameType) {
            GameType.SOLOCROSS, GameType.SOLONOUGHT -> when (buttonImg) {
                playerDrawable -> "Game over: You win!"
                else -> "Game over: You lose :("
            }
            GameType.TWOPLAYERS -> when (buttonImg.toBitmap()) {
                crossBitmap -> "Game over: Player 1 win!"
                else -> "Game over: Player 2 win!"
            }
        }

        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()

        return true
    }
    if (buttonsLeft == 0) {
        Toast.makeText(applicationContext, "Game over: Draw!", Toast.LENGTH_SHORT).show()
        return true
    }
    return false
}