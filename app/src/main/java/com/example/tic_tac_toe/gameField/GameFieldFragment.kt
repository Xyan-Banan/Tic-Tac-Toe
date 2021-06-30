package com.example.tic_tac_toe.gameField

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.tic_tac_toe.R
import com.example.tic_tac_toe.databinding.FragmentGameFieldBinding
import com.example.tic_tac_toe.startMenu.GameType
import kotlin.random.Random

class GameFieldFragment : Fragment() {

    private lateinit var buttons: List<ImageButton>
    private lateinit var availableButtons: MutableList<ImageButton>

    private lateinit var playerDrawable: Drawable
    private lateinit var computerDrawable: Drawable

    private lateinit var gameType: GameType
    private var isGameOver: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentGameFieldBinding.inflate(inflater)

        binding.apply {
            buttons = listOf(
                button1,
                button2,
                button3,
                button4,
                button5,
                button6,
                button7,
                button8,
                button9
            )

            availableButtons = buttons.toMutableList()
        }
        gameType = GameFieldFragmentArgs.fromBundle(requireArguments()).gameType

        if (gameType == GameType.SOLOCROSS) {
//        cross = AppCompatResources.getDrawable(applicationContext, R.drawable.cross)?.toBitmap()!!
            playerDrawable = resources.getDrawable(R.drawable.cross)
            computerDrawable = resources.getDrawable(R.drawable.donut)
        } else {
            playerDrawable = resources.getDrawable(R.drawable.donut)
            computerDrawable = resources.getDrawable(R.drawable.cross)
            computerClick()
        }

        binding.restartBtn.setOnClickListener {
            availableButtons = buttons.toMutableList()
            buttons.forEach {
                it.setImageDrawable(null)
                it.isEnabled = true
            }
        }

        //setting up button on click methods
        binding.apply {
            button1.setOnClickListener {
                if (!isGameOver) {
                    it as ImageButton
                    //human click
                    it.setImageDrawable(playerDrawable)
                    it.isEnabled = false
                    availableButtons.remove(it)

                    isGameOver = isGameOver(
                        buttons,
                        playerDrawable,
                        gameType,
                        availableButtons.size,
                        requireContext()
                    )
                    if (!isGameOver) {
                        //computer click
                        val countAvailable = availableButtons.size
                        if (countAvailable > 0) {
                            val btn = availableButtons[Random.nextInt(countAvailable)]
                            btn.isEnabled = false
                            btn.setImageResource(R.drawable.donut)
                            availableButtons.remove(btn)
                        }
                        isGameOver = isGameOver(
                            buttons,
                            playerDrawable,
                            gameType,
                            availableButtons.size,
                            requireContext()
                        )
                    }
                }
            }

            button2.setOnClickListener {
                if (!isGameOver) {
                    it as ImageButton
                    //human click
                    it.setImageDrawable(playerDrawable)
                    it.isEnabled = false
                    availableButtons.remove(it)

                    isGameOver = isGameOver(
                        buttons,
                        playerDrawable,
                        gameType,
                        availableButtons.size,
                        requireContext()
                    )
                    if (!isGameOver) {
                        //computer click
                        computerClick()
                    }
                }
            }

            button3.setOnClickListener {
                if (!isGameOver) {
                    //human click
                    humanClick(it as ImageButton)

                    if (!isGameOver) {
                        //computer click
                        computerClick()
                    }
                }
            }

            button4.setOnClickListener {
                buttonClick(it as ImageButton)
            }
        }

        //for the rest 5 buttons
        buttons.forEachIndexed { index, imageButton ->
            if (index > 3) {
                imageButton.setOnClickListener { buttonClick(imageButton) }
            }
        }

//        //for all buttons
//        buttons.forEach { imageButton ->
//            imageButton.setOnClickListener { buttonClick(imageButton) }
//        }

        return binding.root
    }

    private fun buttonClick(btn: ImageButton) {

        if (!isGameOver) {
            //human click
            humanClick(btn)
            if (!isGameOver) {
                //computer click
                computerClick()
            }
        }
    }

    private fun humanClick(btn: ImageButton) {
        btn.setImageDrawable(playerDrawable)
        btn.isEnabled = false
        availableButtons.remove(btn)
        isGameOver = isGameOver(
            buttons,
            playerDrawable,
            gameType,
            availableButtons.size,
            requireContext()
        )
    }

    private fun computerClick() {
        val countAvailable = availableButtons.size
        if (countAvailable > 0) {
            val btn = availableButtons[Random.nextInt(countAvailable)]
            btn.isEnabled = false
            btn.setImageDrawable(computerDrawable)
            availableButtons.remove(btn)

            isGameOver = isGameOver(
                buttons,
                playerDrawable,
                gameType,
                availableButtons.size,
                requireContext()
            )
        }
    }
}