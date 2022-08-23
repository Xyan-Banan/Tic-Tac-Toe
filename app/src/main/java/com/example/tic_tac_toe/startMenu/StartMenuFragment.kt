package com.example.tic_tac_toe.startMenu

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tic_tac_toe.databinding.FragmentStartMenuBinding
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class GameType : Parcelable {
    SOLOCROSS,
    SOLONOUGHT,
    TWOPLAYERS
}

class StartMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentStartMenuBinding.inflate(inflater)

        binding.onePlayerGameRadioButton.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.crossesRadioButton.isEnabled = isChecked
            binding.noughtsRadioButton.isEnabled = isChecked
        }

        binding.numPlayersRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            binding.startBtn.isEnabled = true
        }

        binding.startBtn.setOnClickListener {
            val gameType = when (binding.numPlayersRadioGroup.checkedRadioButtonId) {
                binding.twoPlayerGameRadioButton.id -> GameType.TWOPLAYERS
                else -> when (binding.soloGameTypeRadioGroup.checkedRadioButtonId) {
                    binding.noughtsRadioButton.id -> GameType.SOLONOUGHT
                    else -> GameType.SOLOCROSS
                }
            }
            findNavController().navigate(StartMenuFragmentDirections.actionStartGame(gameType))
        }

        return binding.root
    }
}