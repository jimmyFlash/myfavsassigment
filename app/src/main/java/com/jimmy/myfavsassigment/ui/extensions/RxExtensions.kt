package com.jimmy.myfavsassigment.ui.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

// operator defines a method that uses the += (plus assign) operator or other operator signb on extended class
operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}