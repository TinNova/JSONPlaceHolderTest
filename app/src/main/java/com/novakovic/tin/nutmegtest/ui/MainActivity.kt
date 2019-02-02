package com.novakovic.tin.nutmegtest.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.novakovic.tin.nutmegtest.R
import com.novakovic.tin.nutmegtest.ui.base.DisposingActivity
import io.reactivex.rxkotlin.subscribeBy

class MainActivity : DisposingActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getSanatisedPosts().subscribeBy(
                onSuccess = {
                    it
                },
                onError = { it.printStackTrace() })
    }
}
