package com.novakovic.tin.nutmegtest.ui

data class MainViewState(
        val listData: List<MainActivityModel> = listOf(),
        val isLoading: Boolean = true,
        val isDataReady: Boolean = false,
        val isNetworkError: Boolean = false
)

data class MainActivityModel(
        val title: String = "",
        val username: String = ""
)
