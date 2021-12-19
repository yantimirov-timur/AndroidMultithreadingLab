package com.yantimirov_timur.app_task4

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)
        val uri: Uri =
            Uri.parse("https://img2.akspic.ru/originals/2/0/8/6/6/166802-chetyre_svobody-zdanie-neboskreb-legkovyye_avtomobili-vyshka-3840x2160.jpg")
        val drawView = findViewById<SimpleDraweeView>(R.id.my_image_view)
        val button = findViewById<Button>(R.id.btn)
        button.setOnClickListener {
            drawView.setImageURI(uri, this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}