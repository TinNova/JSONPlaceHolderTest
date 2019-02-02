package com.novakovic.tin.nutmegtest.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.novakovic.tin.nutmegtest.R
import com.novakovic.tin.nutmegtest.model.UserPostModel
import com.novakovic.tin.nutmegtest.ui.base.DisposingActivity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DisposingActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getSanatisedPosts().subscribeBy(
                onSuccess = {
                    setData(it)
                },
                onError = { it.printStackTrace() })

        setupRecyclerView()
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
}
