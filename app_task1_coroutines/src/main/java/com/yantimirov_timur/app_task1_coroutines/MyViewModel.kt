package com.yantimirov_timur.app_task1_coroutines


import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel(val textSecondsElapsed: TextView) : ViewModel() {
    var secondsElapsed = 0

    init {
        viewModelScope.launch {
            while (true) {
                delay(1000L)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = "SecondElapsed: $secondsElapsed"
                }
            }
        }
    }

}