package com.features.test_app_githubusersearch.app.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.features.test_app_githubusersearch.app.App
import com.features.test_app_githubusersearch.app.AppComponent

open class BaseActivity: AppCompatActivity() {

    val appComponent: AppComponent
        get() { return (application as App).appComponent }


    fun hideSoftKeyboard(activity: Activity, view: View) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0)
    }
}