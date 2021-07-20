package com.example.tic_tac_toe.gameField

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.content.res.AppCompatResources
import com.example.tic_tac_toe.R
import com.example.tic_tac_toe.databinding.FragmentGameFieldBinding
import com.example.tic_tac_toe.startMenu.GameType

class GameFieldFragment : Fragment() {

    private lateinit var binding: FragmentGameFieldBinding
    private lateinit var cross: Drawable
    private lateinit var donut: Drawable

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
        binding = FragmentGameFieldBinding.inflate(inflater)
        cross = AppCompatResources.getDrawable(requireContext(), R.drawable.cross)!!
        donut = AppCompatResources.getDrawable(requireContext(), R.drawable.donut)!!

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

        when (gameType) {
            GameType.SOLOCROSS -> {
                playerDrawable = cross
                computerDrawable = donut
                setSoloButtons()
            }
            GameType.SOLONOUGHT -> {
                playerDrawable = donut
                computerDrawable = cross
                setSoloButtons()
                computerClick()
            }
            GameType.TWOPLAYERS -> {
                playerDrawable = cross
                computerDrawable = donut
                setMultiButtons()
            }
        }

        binding.restartBtn.setOnClickListener {
            availableButtons = buttons.toMutableList()
            buttons.forEach {
                it.setImageDrawable(null)
                it.isEnabled = true
            }
            if (gameType == GameType.SOLONOUGHT) {
                playerDrawable = donut
                computerDrawable = cross
                computerClick()
            } else {
                playerDrawable = cross
                computerDrawable = donut
            }
            isGameOver = false
        }

        return binding.root
    }

    private fun setSoloButtons() {
        //setting up button on click methods
        binding.apply {
            button1.setOnClickListener {
                if (!isGameOver) {
                    it as ImageButton
                    //human click
                    it.setImageDrawable(playerDrawable)
                    it.isEnabled = false
                    availableButtons.remove(it)

                    isGameOver = isGameOver(buttons, gameType, requireContext())
                    if (!isGameOver) {
                        //computer click
                        if (availableButtons.isNotEmpty()) {
                            val btn = availableButtons.random()
                            btn.isEnabled = false
                            btn.setImageResource(R.drawable.donut)
                            availableButtons.remove(btn)
                        }
                        isGameOver = isGameOver(buttons, gameType, requireContext())
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

                    isGameOver = isGameOver(buttons, gameType, requireContext())
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
    }

    private fun setMultiButtons() {
        buttons.forEach { imageButton ->
            imageButton.setOnClickListener {
                if (!isGameOver) {
                    humanClick(imageButton)
                    swapPlayers()
                }
            }
        }
    }

    private fun swapPlayers() {
        val temp = playerDrawable
        playerDrawable = computerDrawable
        computerDrawable = temp
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
        isGameOver = isGameOver(buttons, gameType, requireContext())
    }

    private fun computerClick() {
        if (availableButtons.isNotEmpty()) {
            val btn = availableButtons.random()
            btn.isEnabled = false
            btn.setImageDrawable(computerDrawable)
            availableButtons.remove(btn)

            isGameOver = isGameOver(buttons, gameType, requireContext())
        }
    }
}