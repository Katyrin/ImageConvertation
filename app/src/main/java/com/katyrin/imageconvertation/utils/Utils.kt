package com.katyrin.imageconvertation.utils

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showAlertDialog(title: String, message: String?) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .create()
        .show()
}