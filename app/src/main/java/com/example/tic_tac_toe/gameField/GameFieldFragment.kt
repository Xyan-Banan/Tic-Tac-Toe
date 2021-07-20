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

        //TODO("Настроить игру в зависимости от выбранного режима игры")

        return binding.root
    }

    private fun setSoloButtons() {
        //TODO("Настроить кнопки для одного игрока")
        //setting up button on click methods
        binding.apply {
            button1.setOnClickListener {
                it as ImageButton
                //TODO("Настроить кнопку 1")
            }
        }
    }

    private fun setMultiButtons() {
        //TODO("Настроить кнопки для двух игроков")
    }

    private fun swapPlayers() {
        val temp = playerDrawable
        playerDrawable = computerDrawable
        computerDrawable = temp
    }
}