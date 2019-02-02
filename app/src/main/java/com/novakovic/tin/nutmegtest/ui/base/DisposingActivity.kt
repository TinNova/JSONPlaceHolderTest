package com.novakovic.tin.nutmegtest.ui.base

import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Base Activity class which provides a CompositeDisposable object for clearing observables.
 */
abstract class DisposingActivity : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}
