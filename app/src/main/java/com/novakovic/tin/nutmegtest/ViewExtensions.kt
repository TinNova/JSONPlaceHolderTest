package com.novakovic.tin.nutmegtest

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.novakovic.tin.nutmegtest.ui.MainViewModel


/**
 *  setting visibility.
 */
fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun Context.showToast(msg: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, msg, length).show()
}

/**
 * Simplify ViewModel lookup
 */
inline fun <reified T : ViewModel> AppCompatActivity.findViewModel(viewModelFactory: ViewModelFactory<MainViewModel>): T =
        ViewModelProviders.of(this, viewModelFactory).get(T::class.java)

