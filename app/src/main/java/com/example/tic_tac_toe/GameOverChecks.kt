package com.example.tic_tac_toe

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageButton
import android.widget.Toast

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

private fun isGameOver(buttons: List<ImageButton>, playerDrawable: Drawable, applicationContext: Context): Boolean {
    val buttonImg = findGameOverRow(buttons)
    if (buttonImg != null) {
        if (buttonImg == playerDrawable) {
            Toast.makeText(applicationContext, "Game over: You win!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, "Game over: You lose :(", Toast.LENGTH_LONG)
                .show()
        }
        return true
    }
    return false
}