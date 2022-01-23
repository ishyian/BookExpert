package com.revolution.bookexpert.other.custom.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T : Any> Observable<T>.async(): Observable<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> T.just(): Observable<T> {
    return Observable.just(this)
}

fun <T> Observable<T>.item(): T {
    return this.blockingFirst()
}