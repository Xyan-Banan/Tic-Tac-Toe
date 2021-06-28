package com.example.tic_tac_toe

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding

    private lateinit var buttons: List<ImageButton>
    private lateinit var availableButtons: MutableList<ImageButton>

    private lateinit var cross: Drawable
    private lateinit var donut: Drawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
