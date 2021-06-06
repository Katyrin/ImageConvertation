package com.katyrin.imageconvertation.presenter

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView{
    fun setImage(imageBitmap: Bitmap?)
    fun init()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openImage()
    fun showProgressConversion()
    fun showSuccess()
    fun hideProgressConversion()
    fun showError()
}