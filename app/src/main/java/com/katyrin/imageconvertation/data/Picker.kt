package com.katyrin.imageconvertation.data

import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Single

interface Picker {
    fun pick(data: Intent?, contentResolver: ContentResolver): Single<Bitmap?>
}