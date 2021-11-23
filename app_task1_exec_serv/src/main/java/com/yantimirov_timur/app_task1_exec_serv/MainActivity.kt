package com.yantimirov_timur.app_task1_exec_serv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import java.util.concurrent.*

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView
    private lateinit var handler: Handler
    private var shutDowned = false
    private lateinit var singleThreadExecutor:ExecutorService

    private fun runSingleThreadExecutor() {
        singleThreadExecutor = MyApp().singleThreadExecutor
        singleThreadExecutor.execute(object : Runnable {
            override fun run() {
                if (!shutDowned) {
                    textSecondsElapsed.post {
                        Log.d("Executor", "is execute")
                        textSecondsElapsed.text =
                            getString(R.string.second_elapsed, secondsElapsed++)
                    }
                    handler.postDelayed(this, 1000)
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler(Looper.getMainLooper())
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onResume() {
        shutDowned = false
        runSingleThreadExecutor()
        Log.d("App", "is open")
        super.onResume()
    }

    override fun onPause() {
        singleThreadExecutor.shutdown()
        shutDowned = singleThreadExecutor.isShutdown
        Log.d("App", "is collapsed")
        super.onPause()
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