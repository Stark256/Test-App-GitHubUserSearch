package com.features.test_app_githubusersearch.app.ui.details

import com.features.test_app_githubusersearch.api.ApiService
import dagger.Module
import dagger.Provides

@Module
class DetailsViewModelModule {

    @Provides
    fun providesDetailsViewModelFactory(apiService: ApiService) : DetailsViewModelFactory {
         return DetailsViewModelFactory(apiService)
    }

}