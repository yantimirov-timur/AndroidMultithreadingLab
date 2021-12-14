package ru.spbstu.icc.kspt.lab2.continuewatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView
    private lateinit var handler: Handler
    private val threadTag = "Thread"
    private val appTag = "Application"

    private val backgroundThread = object : Runnable {
        override fun run() {
            textSecondsElapsed.post {
                textSecondsElapsed.text = getString(R.string.second_elapsed, secondsElapsed++)
            }
            Log.d("Time", "${System.currentTimeMillis()}")
            handler.postDelayed(this, 1000)
        }
    }

    private fun runTimer() {
        Log.d(threadTag, "is run")
        handler = Handler(Looper.getMainLooper())
        backgroundThread.run()
    }

    private fun stopTimer() {
        Log.d(threadTag, "is stop")
        handler.removeCallbacks(backgroundThread)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onPause() {
        Log.d("onPause()", "is called")
        Log.d(appTag, "is collapse")
        stopTimer()
        super.onPause()
    }

    override fun onResume() {
        Log.d("onResume()", "is called")
        Log.d(appTag, "is open")
        runTimer()
        super.onResume()
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