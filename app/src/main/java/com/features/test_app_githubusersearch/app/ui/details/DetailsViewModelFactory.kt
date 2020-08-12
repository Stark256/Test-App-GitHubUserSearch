package com.features.test_app_githubusersearch.app.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.features.test_app_githubusersearch.api.ApiService
import javax.inject.Inject

class DetailsViewModelFactory @Inject constructor(private val api: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(api) as T
    }
}