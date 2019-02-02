package com.novakovic.tin.nutmegtest.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.novakovic.tin.nutmegtest.R
import com.novakovic.tin.nutmegtest.gone
import com.novakovic.tin.nutmegtest.model.UserPostModel
import com.novakovic.tin.nutmegtest.ui.base.DisposingActivity
import com.novakovic.tin.nutmegtest.visible
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DisposingActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getSanatisedPosts()
        progressLoading.setOnClickListener { getSanatisedPosts() }
        setupRecyclerView()
    }

    private fun getSanatisedPosts() {
        viewModel.getSanatisedPosts().subscribeBy(
                onSuccess = {
                    setData(it)
                    setNoDataScreen(false)
                },
                onError = {
                    it.printStackTrace()
                    setNoDataScreen(true)
                })
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = postAdapter
    }

    private fun setData(posts: MutableList<UserPostModel>) {
        postAdapter.setData(posts)
    }

    private fun setNoDataScreen(network: Boolean) {
        if (network) {
            recyclerView.gone()
            noNetworkMessages.visible()
        } else {
            recyclerView.visible()
            noNetworkMessages.gone()
        }
    }
}
