package com.example.tic_tac_toe.startMenu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartMenuViewModel: ViewModel() {
    val soloGameChecked = MutableLiveData(false)
}