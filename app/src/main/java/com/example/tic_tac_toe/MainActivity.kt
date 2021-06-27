package com.example.tic_tac_toe

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
//import com.example.tic_tac_toe.databinding.ActivityMainBinding
import com.example.tic_tac_toe.startmenu.isGameOver
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding

    private lateinit var availableButtons: MutableList<ImageButton>
    private lateinit var buttons: List<ImageButton>

    private lateinit var cross: Drawable
    private lateinit var donut: Drawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        cross = AppCompatResources.getDrawable(applicationContext, R.drawable.cross)?.toBitmap()!!
//        cross = resources.getDrawable(R.drawable.cross)
//        donut = resources.getDrawable(R.drawable.donut)
//
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//
//        binding.apply {
//            buttons = listOf(
//                button1,
//                button2,
//                button3,
//                button4,
//                button5,
//                button6,
//                button7,
//                button8,
//                button9
//            )
//
//            availableButtons = buttons.toMutableList()
//
//            buttons.forEach { it.setImageDrawable(null) }
//        }
//
//        binding.restartBtn.setOnClickListener {
//            availableButtons = buttons.toMutableList()
//            buttons.forEach {
//                it.setImageDrawable(null)
//                it.isEnabled = true
//            }
//        }
//
//        //setting up button on click methods
//        binding.apply {
//            button1.setOnClickListener {
//                if (!isGameOver(buttons, cross, applicationContext)) {
//                    it as ImageButton
//                    //human click
//                    it.setImageDrawable(cross)
//                    it.isEnabled = false
//                    availableButtons.remove(it)
//
//                    if (!isGameOver(buttons, cross, applicationContext)) {
//                        //computer click
//                        val countAvailable = availableButtons.size
//                        if (countAvailable > 0) {
//                            val btn = availableButtons[Random.nextInt(countAvailable)]
//                            btn.isEnabled = false
//                            btn.setImageResource(R.drawable.donut)
//                            availableButtons.remove(btn)
//                        }
//
//                    }
//                }
//            }
//
//            button2.setOnClickListener {
//                if (!isGameOver(buttons, cross, applicationContext)) {
//                    it as ImageButton
//                    //human click
//                    it.setImageDrawable(cross)
//                    it.isEnabled = false
//                    availableButtons.remove(it)
//
//                    if (!isGameOver(buttons, cross, applicationContext)) {
//                        //computer click
//                        computerClick()
//                    }
//                }
//            }
//
//            button3.setOnClickListener {
//                if (!isGameOver(buttons, cross, applicationContext)) {
//                    //human click
//                    humanClick(it as ImageButton)
//                    if (!isGameOver(buttons, cross, applicationContext)) {
//                        //computer click
//                        computerClick()
//                    }
//                }
//            }
//
//            button4.setOnClickListener {
//                buttonClick(it as ImageButton)
//            }
//
//
//            buttons.forEachIndexed { index, imageButton ->
//                if (index > 3) {
//                    imageButton.setOnClickListener { buttonClick(imageButton) }
//                }
//            }
//        }
    }

    private fun buttonClick(btn: ImageButton) {
        if (!isGameOver(buttons, cross, applicationContext)) {
            //human click
            humanClick(btn)
            if (!isGameOver(buttons, cross, applicationContext)) {
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