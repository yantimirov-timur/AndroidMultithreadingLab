package com.yantimirov_timur.app_task1_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView
    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    private fun runTimer() {
        job = MainScope().launch {
            while (true) {
                textSecondsElapsed.text = getString(R.string.second_elapsed, secondsElapsed++)
                Log.d("Coroutine", "is working")
                delay(1000L)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("App", "is open")
        runTimer()
    }

    override fun onPause() {
        super.onPause()
        Log.d("App", "is collapse")
        job.cancel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(getString(R.string.second_elapsed), secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        secondsElapsed = savedInstanceState.getInt(getString(R.string.second_elapsed))
        super.onRestoreInstanceState(savedInstanceState)
    }
}