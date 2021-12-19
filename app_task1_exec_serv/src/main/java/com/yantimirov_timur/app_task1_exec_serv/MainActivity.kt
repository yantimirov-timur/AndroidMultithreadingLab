package com.yantimirov_timur.app_task1_exec_serv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.util.concurrent.*

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView
    private lateinit var executorService: ExecutorService
    lateinit var futureTask: FutureTask<Any>

    private fun runSingleThreadExecutor() {
        futureTask = FutureTask({
            while (!futureTask.isDone) {
                textSecondsElapsed.post {
                    textSecondsElapsed.text =
                        getString(R.string.second_elapsed, secondsElapsed++)
                }
                Log.d("executor", "executed")
                Thread.sleep(1000)
            }
        }, null)
        executorService = (application as MyApp).singleThreadExecutor
        executorService.execute(futureTask)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onResume() {
        runSingleThreadExecutor()
        Log.d("App", "is open")
        super.onResume()
    }

    override fun onPause() {
        futureTask.cancel(true)
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