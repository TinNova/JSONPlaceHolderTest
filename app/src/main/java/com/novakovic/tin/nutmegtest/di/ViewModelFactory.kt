package com.novakovic.tin.nutmegtest.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

/**
 * AndroidViewModel and Dagger 2 don't work together out the box, a ViewModelFactory is required
 * Coded taken from here: https://stackoverflow.com/a/48819177
 * */
class ViewModelFactory<T : ViewModel> @Inject constructor(
        private val viewModel: Lazy<T>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel.get() as T
}
