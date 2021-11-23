package com.yantimirov_timur.app_task1_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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
        job = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.second_elapsed, secondsElapsed++)
                }
                delay(1000L)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        runTimer()
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }

}