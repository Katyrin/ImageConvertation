package com.katyrin.imageconvertation.presenter

import android.graphics.Bitmap
import com.katyrin.imageconvertation.ui.ImageConverter
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class MainPresenter(
    private val converter: ImageConverter,
    private val uiScheduler: Scheduler
) : MvpPresenter<MainView>() {

    private var disposable: Disposable? = null
    private var imageBitmap: Bitmap? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setImage(imageBitmap)
    }

    fun convertImage() {
        viewState.openImage()
    }

    fun setImage(bitmap: Bitmap?) {
        imageBitmap = bitmap
        viewState.setImage(imageBitmap)
    }

    fun savePngImage() {
        viewState.showProgressConversion()
        disposable = converter.convert(imageBitmap)
            .observeOn(uiScheduler)
            .subscribe({
                viewState.hideProgressConversion()
                viewState.showSuccess()
            }, {
                viewState.hideProgressConversion()
                viewState.showError()
            })
    }
}