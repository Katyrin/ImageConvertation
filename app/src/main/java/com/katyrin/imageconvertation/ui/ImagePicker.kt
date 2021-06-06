package com.katyrin.imageconvertation.ui

import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.katyrin.imageconvertation.data.Picker
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ImagePicker : Picker {
    override fun pick(data: Intent?, contentResolver: ContentResolver): Single<Bitmap?> = Single
        .fromCallable { getBitmap(data, contentResolver) }
        .subscribeOn(Schedulers.io())

    private fun getBitmap(data: Intent?, contentResolver: ContentResolver): Bitmap? {
        var bitmap: Bitmap? = null
        data?.data?.let { uri ->
            val bytes =
                contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }
            bytes?.let { byteArray ->
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            }
        }
        return bitmap
    }
}