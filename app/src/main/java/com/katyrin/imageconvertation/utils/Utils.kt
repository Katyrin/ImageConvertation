package com.katyrin.imageconvertation.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.katyrin.imageconvertation.R

fun Context.createAlertDialog(
    title: String,
    message: String?,
    onClick: () -> Unit
): AlertDialog =
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setCancelable(false)
        .setMessage(message)
        .setNegativeButton(getString(R.string.cancel)) { dialogInterface, _ ->
            onClick()
            dialogInterface.dismiss()
        }
        .create()
