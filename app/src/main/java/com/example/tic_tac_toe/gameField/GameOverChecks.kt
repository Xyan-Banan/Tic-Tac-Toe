package com.example.tic_tac_toe.gameField

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.example.tic_tac_toe.R
import com.example.tic_tac_toe.startMenu.GameType

fun <T> List<T>.allEqual(): Boolean = all { it == first() }

private fun findGameOverRow(images: List<Bitmap?>): Bitmap? {

    val winLists = listOf(
        listOf(images[0], images[1], images[2]), //top line
        listOf(images[3], images[4], images[5]), //center line
        listOf(images[6], images[7], images[8]), //bottom line
        listOf(images[0], images[3], images[6]), //left side
        listOf(images[1], images[4], images[7]), //mid side
        listOf(images[2], images[5], images[8]), //right side
        listOf(images[0], images[4], images[8]), //left diagonal
        listOf(images[2], images[4], images[6])  //right diagonal
    )

    return winLists.firstOrNull { list -> list[0] != null && list.allEqual() }?.first()

//    val (crosses, noughts) = buttonsNotNull.partition { it.drawable.toBitmap() == crossBitmap }
//
//    for (winButtons in winLists) {
//        if (crosses.containsAll(winButtons)) return winButtons.first().drawable
//        if (noughts.containsAll(winButtons)) return winButtons.first().drawable
//    }
//    return null
}

val ImageButton.bitmap
    get() = drawable?.toBitmap()

fun isGameOver(
    buttons: List<ImageButton>,
    gameType: GameType,
    context: Context
): Boolean {
    val images = buttons.map { it.bitmap }
    val imagesNotNull = images.filterNotNull()
    //break if not enough steps on game field
    if (imagesNotNull.size < 5)
        return false

    val crossBitmap = AppCompatResources.getDrawable(context, R.drawable.cross)!!.toBitmap()
    val gameOverBitmap = findGameOverRow(images)

    val msg =
        if (gameOverBitmap != null) {
            when (gameType) {
                GameType.SOLOCROSS -> when (gameOverBitmap) {
                    crossBitmap -> "Game over: You win!"
                    else -> "Game over: You lose :("
                }
                GameType.SOLONOUGHT -> when (gameOverBitmap) {
                    crossBitmap -> "Game over: You lose :("
                    else -> "Game over: You win!"
                }
                GameType.TWOPLAYERS -> when (gameOverBitmap) {
                    crossBitmap -> "Game over: Player 1 win!"
                    else -> "Game over: Player 2 win!"
                }
            }
        } else {
            //if there is no available buttons - draw
            if (imagesNotNull.size >= buttons.size) {
                "Game over: Draw!"
            } else {
                return false
            }
        }
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    return true
}
