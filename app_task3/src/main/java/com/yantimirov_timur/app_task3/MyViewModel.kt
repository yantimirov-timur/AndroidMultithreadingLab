package com.yantimirov_timur.app_task3

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import java.io.BufferedInputStream
import java.net.URL

class MyViewModel : ViewModel() {
    private val url = "https://storge.pic2.me/cm/2560x1440/346/556612eab5b78.jpg"
    val getImage: LiveData<Drawable> =
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val img = downloadImageByUrl(url)
            emit(img)
        }

    private fun downloadImageByUrl(urlImage: String): Drawable {
        val url = URL(urlImage)
        val input = url.openStream()
        val buf = BufferedInputStream(input)
        val bitmap = BitmapFactory.decodeStream(buf)
        input?.close()
        buf.close()
        return BitmapDrawable(bitmap)
    }

}

