package com.example.tic_tac_toe.startmenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartMenuViewModel: ViewModel() {
    val soloGameChecked = MutableLiveData<Boolean>()
}