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
    val singleThreadExecutor: ExecutorService = Executors.newSingleThreadExecutor()
}