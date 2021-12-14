package com.yantimirov_timur.app_task2

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DownloaderImageApp: Application() {
    val singleThreadExecutor: ExecutorService = Executors.newSingleThreadExecutor()
}