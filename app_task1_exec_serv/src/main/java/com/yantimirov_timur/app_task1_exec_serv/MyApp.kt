package com.yantimirov_timur.app_task1_exec_serv

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import java.util.concurrent.*

class MyApp : Application() {
    //val executorService = Executors.newFixedThreadPool(4)

    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
    private val KEEP_ALIVE_TIME = 1
    private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS


}