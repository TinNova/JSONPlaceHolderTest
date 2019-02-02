package com.novakovic.tin.nutmegtest.ui

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.novakovic.tin.nutmegtest.R
import io.reactivex.rxkotlin.subscribeBy

class MainActivity : AppCompatActivity() {

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
