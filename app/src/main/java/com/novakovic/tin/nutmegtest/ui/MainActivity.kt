package com.novakovic.tin.nutmegtest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.novakovic.tin.nutmegtest.R
import com.novakovic.tin.nutmegtest.ViewModelFactory
import com.novakovic.tin.nutmegtest.gone
import com.novakovic.tin.nutmegtest.ui.base.DisposingActivity
import com.novakovic.tin.nutmegtest.visible
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DisposingActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private lateinit var viewModel: MainViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

        viewModel.viewState.observe(this, Observer<MainViewState> {
            it?.let { observe(it) }
        })

        viewModel.initView()

        setupRecyclerView()
        loading_icon.setOnClickListener { viewModel.getSanatisedPosts() }
    }

    private fun observe(it: MainViewState) {
        when (it.isDataReady) {
            true -> showData(it.listData)
            false -> hideData()
        }
        when (it.isLoading) {
            true -> loading_icon.visible()
            false -> loading_icon.gone()
        }
        when (it.isNetworkError) {
            true -> network_error.visible()
            false -> network_error.gone()
        }
    }

    private fun showData(listData: List<MainActivityModel>) {
        recyclerView.visible()
        postAdapter.setData(listData)
    }

    private fun hideData() {
        recyclerView.gone()
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = postAdapter
    }
}
