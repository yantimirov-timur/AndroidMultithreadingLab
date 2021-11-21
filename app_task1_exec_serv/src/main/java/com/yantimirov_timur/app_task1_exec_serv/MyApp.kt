package com.yantimirov_timur.app_task1_exec_serv

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import java.util.concurrent.Executors

class MyApp : Application() {
    val executorService = Executors.newFixedThreadPool(4)

    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()

    override fun onCreate() {
        super.onCreate()
        executorService.execute(object: Runnable{
            override fun run() {
                Log.d("MyApp","thread")
            }
        })

        Toast.makeText(this, "App onCreate", Toast.LENGTH_LONG).show()
    }

}