package com.features.test_app_githubusersearch.app

import android.app.Application
import com.features.test_app_githubusersearch.api.ApiModule
import com.features.test_app_githubusersearch.api.ApiService
import com.features.test_app_githubusersearch.app.ui.details.DetailsActivity
import com.features.test_app_githubusersearch.app.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    ApiModule::class
))
interface AppComponent {

    fun application() : Application
    fun apiService() : ApiService

    fun inject(mainActivity: MainActivity)
    fun inject(detailsActivity: DetailsActivity)
    fun inject(app: Application)
}