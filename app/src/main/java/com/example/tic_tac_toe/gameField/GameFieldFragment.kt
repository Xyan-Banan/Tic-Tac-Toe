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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameFieldFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFieldFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var buttons: List<ImageButton>
    private lateinit var availableButtons: MutableList<ImageButton>

    private lateinit var cross: Drawable
    private lateinit var donut: Drawable
//    private lateinit var player: Drawable

    private lateinit var gameType: GameType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentGameFieldBinding.inflate(inflater)

        gameType = GameFieldFragmentArgs.fromBundle(requireArguments()).gameType

//        cross = AppCompatResources.getDrawable(applicationContext, R.drawable.cross)?.toBitmap()!!
        cross = resources.getDrawable(R.drawable.cross)
        donut = resources.getDrawable(R.drawable.donut)

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
                if (!isGameOver(buttons, cross, requireContext())) {
                    it as ImageButton
                    //human click
                    it.setImageDrawable(cross)
                    it.isEnabled = false
                    availableButtons.remove(it)

                    if (!isGameOver(buttons, cross, requireContext())) {
                        //computer click
                        val countAvailable = availableButtons.size
                        if (countAvailable > 0) {
                            val btn = availableButtons[Random.nextInt(countAvailable)]
                            btn.isEnabled = false
                            btn.setImageResource(R.drawable.donut)
                            availableButtons.remove(btn)
                        }

                    }
                }
            }

            button2.setOnClickListener {
                if (!isGameOver(buttons, cross, requireContext())) {
                    it as ImageButton
                    //human click
                    it.setImageDrawable(cross)
                    it.isEnabled = false
                    availableButtons.remove(it)

                    if (!isGameOver(buttons, cross, requireContext())) {
                        //computer click
                        computerClick()
                    }
                }
            }

            button3.setOnClickListener {
                if (!isGameOver(buttons, cross, requireContext())) {
                    //human click
                    humanClick(it as ImageButton)
                    if (!isGameOver(buttons, cross, requireContext())) {
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GameFieldFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameFieldFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun buttonClick(btn: ImageButton) {

        if (!isGameOver(buttons, cross, requireContext())) {
            //human click
            humanClick(btn)
            if (!isGameOver(buttons, cross, requireContext())) {
                //computer click
                computerClick()
            }
        }
    }

    private fun humanClick(btn: ImageButton) {
        btn.setImageDrawable(cross)
        btn.isEnabled = false
        availableButtons.remove(btn)
    }

    private fun computerClick() {
        val countAvailable = availableButtons.size
        if (countAvailable > 0) {
            val btn = availableButtons[Random.nextInt(countAvailable)]
            btn.isEnabled = false
            btn.setImageDrawable(donut)
            availableButtons.remove(btn)
        }
    }
}