package com.katyrin.imageconvertation.data

import io.reactivex.rxjava3.core.Completable

interface Converter {
    fun convert(image: Image?): Completable
}