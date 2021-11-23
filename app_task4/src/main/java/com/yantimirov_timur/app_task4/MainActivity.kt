package com.yantimirov_timur.app_task4

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)
        val uri: Uri = Uri.parse("https://storge.pic2.me/cm/2560x1440/346/556612eab5b78.jpg")
        val drawView = findViewById<SimpleDraweeView>(R.id.my_image_view)
        val button = findViewById<Button>(R.id.btn)
        button.setOnClickListener {
            drawView.setImageURI(uri, this)
        }
    }
}