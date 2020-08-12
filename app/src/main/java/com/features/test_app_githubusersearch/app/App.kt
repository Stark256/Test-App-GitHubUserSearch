package com.features.test_app_githubusersearch.app

import android.app.Application
import com.features.test_app_githubusersearch.R
import com.features.test_app_githubusersearch.api.ApiModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .apiModule(ApiModule(getString(R.string.base_url)))
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)

    }
}