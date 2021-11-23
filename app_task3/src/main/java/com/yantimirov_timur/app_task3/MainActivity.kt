package com.yantimirov_timur.app_task3

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableContainer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.*
import java.io.BufferedInputStream
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private val address = "https://storge.pic2.me/cm/2560x1440/346/556612eab5b78.jpg"
    var img: Drawable = DrawableContainer()
    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.download_btn)

        button.setOnClickListener {
            downloadImageAsync { result ->
                runOnUiThread {
                    setImage(result)
                }
            }
        }
    }

    private fun downloadImageAsync(setResult: (result: Drawable) -> Unit) {
        job = CoroutineScope(Dispatchers.Default).launch {
            img = downloadImage(address)
            setResult(img)
        }
    }

    private fun downloadImage(urlImage: String): Drawable {
        val url = URL(urlImage)
        val input = url.openStream()
        val buf = BufferedInputStream(input)
        val bitmap = BitmapFactory.decodeStream(buf)
        input?.close()
        buf.close()
        return BitmapDrawable(resources, bitmap)
    }

    private fun setImage(drawable: Drawable) {
        imageView.background = drawable
    }
}