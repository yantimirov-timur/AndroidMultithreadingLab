package com.yantimirov_timur.app_task2

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableContainer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import java.io.BufferedInputStream
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var executorService: ExecutorService
    private val address = "https://storge.pic2.me/cm/2560x1440/346/556612eab5b78.jpg"
    private var img: Drawable = DrawableContainer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.download_btn)
        executorService = Executors.newSingleThreadExecutor()

        button.setOnClickListener {
            downloadImageInBackground { result ->
                runOnUiThread {
                    setImage(result)
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun downloadImageInBackground(callback: (result: Drawable) -> Unit) {
        executorService.execute {
            img = downloadImage(address)
            callback(img)
            Log.d("Image", "Downloaded")
        }
        executorService.shutdown()
    }

    private fun downloadImage(urlImage: String): Drawable {
        val url = URL(urlImage)
        val input = url.openStream()
        val buf = BufferedInputStream(input)
        val bMap = BitmapFactory.decodeStream(buf)
        input?.close()
        buf.close()
        return BitmapDrawable(resources, bMap)
    }

    private fun setImage(drawable: Drawable) {
        imageView.background = drawable
    }
}