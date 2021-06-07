package com.katyrin.imageconvertation.presenter

import com.katyrin.imageconvertation.data.Image
import com.katyrin.imageconvertation.ui.ImageConverter
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class MainPresenter(
    private val converter: ImageConverter,
    private val uiScheduler: Scheduler
) : MvpPresenter<MainView>() {

    private var disposable: Disposable? = null
    private var image: Image? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setImage(image)
    }

    fun convertImage() {
        viewState.openImage()
    }

    fun setImage(image: Image?) {
        this.image = image
        viewState.setImage(image)
    }

    fun savePngImage() {
        viewState.showProgressConversion()
        disposable = converter.convert(image)
            .observeOn(uiScheduler)
            .subscribe({
                viewState.hideProgressConversion()
                viewState.showSuccess()
            }, { throwable ->
                viewState.hideProgressConversion()
                viewState.showError(throwable.message)
            })
    }

    fun stopConvert() {
        if (disposable != null)
            disposable?.dispose()
        disposable = null
        viewState.showCancelMessage()
    }
}