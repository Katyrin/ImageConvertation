package com.katyrin.imageconvertation.ui

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment.DIRECTORY_PICTURES
import com.katyrin.imageconvertation.data.Converter
import com.katyrin.imageconvertation.data.Image
import com.katyrin.imageconvertation.utils.DEFAULT_IMAGE_NAME
import com.katyrin.imageconvertation.utils.IMAGE_QUALITY
import com.katyrin.imageconvertation.utils.PNG
import com.katyrin.imageconvertation.utils.TIME_IO_THREAD_SLEEP
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class ImageConverter(private val context: Context) : Converter {
    override fun convert(image: Image?): Completable = Completable.fromAction {
        try {
            Thread.sleep(TIME_IO_THREAD_SLEEP)
        } catch (e: InterruptedException) {
            return@fromAction
        }

        val imageName = "${image?.imageName ?: DEFAULT_IMAGE_NAME}$PNG"
        val file = File(context.getExternalFilesDir(DIRECTORY_PICTURES), imageName)
        FileOutputStream(file).use { stream ->
            image?.bitmap?.compress(Bitmap.CompressFormat.PNG, IMAGE_QUALITY, stream)
        }
    }.subscribeOn(Schedulers.io())
}