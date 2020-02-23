package com.novakovic.tin.nutmegtest.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.novakovic.tin.nutmegtest.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private lateinit var viewModel: MainViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        viewModel = findViewModel(viewModelFactory)
        viewModel.initView()
        observeViewState()
        setupRecyclerView()

        loading_icon.setOnClickListener { viewModel.getPosts() }
    }

    private fun observeViewState() {
        viewModel.viewState.observe(this, Observer<MainViewState> {
            it?.let {
                when (it.isDataReady) {
                    true -> showData(it.listData)
                    false -> recyclerView.gone()
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
        })
    }

    private fun showData(listData: List<MainActivityModel>) {
        recyclerView.visible()
        postAdapter.setData(listData)

        this.showToast("message")
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = postAdapter
    }
}
