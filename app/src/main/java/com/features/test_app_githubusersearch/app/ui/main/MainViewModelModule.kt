package com.features.test_app_githubusersearch.app.ui.main

import com.features.test_app_githubusersearch.api.ApiService
import dagger.Module
import dagger.Provides

@Module
class MainViewModelModule {

    @Provides
    fun providesMainViewModelFactory(apiService: ApiService) : MainViewModelFactory {
        return MainViewModelFactory(apiService)
    }
}