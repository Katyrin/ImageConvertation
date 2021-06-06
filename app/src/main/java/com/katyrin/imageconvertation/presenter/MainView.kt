package com.katyrin.imageconvertation.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView{
    fun init()
    fun openImage()
    fun convertImage()
    fun saveImage()
}