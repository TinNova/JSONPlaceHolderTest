package com.novakovic.tin.nutmegtest

import android.view.View

/**
 *  Simple way to set view visibility to gone.
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 *  Simple way to make view visible.
 */
fun View.visible() {
    visibility = View.VISIBLE
}
