package com.features.test_app_githubusersearch.app.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.features.test_app_githubusersearch.R
import com.features.test_app_githubusersearch.api.models.UserDataModel
import com.features.test_app_githubusersearch.app.ui.BaseActivity
import com.features.test_app_githubusersearch.app.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainUsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)
        this.viewModel = ViewModelProvider(viewModelStore, this.factory).get(MainViewModel::class.java)

        this.adapter = MainUsersAdapter(object : MainUsersAdapter.UserClickListener {
            override fun onUserClick(user: UserDataModel) {
                // TODO open details activity
                startActivity(DetailsActivity.newInstance(this@MainActivity, user))
            }
        })

        rv_main.adapter = adapter
        rv_main.layoutManager = LinearLayoutManager(this)


        tiet_main_search.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(text: Editable?) {}
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.searchUsers(text.toString())
            }
        })

//        tiet_main_search.setOnEditorActionListener { v, actionId, event ->
//            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
//                hideSoftKeyboard(this, tiet_main_search)
//                viewModel.searchUsers(tiet_main_search.text.toString())
//            }
//            return@setOnEditorActionListener false
//        }

        this.viewModel.apply {
            users.observe(this@MainActivity, Observer<ArrayList<UserDataModel>> {
                adapter.replaceAll(it)
            })
            isLoaded.observe(this@MainActivity, Observer<LoadingType> { isLoaded ->
                when(isLoaded) {
                    LoadingType.ERROR -> {
                        rv_main.visibility = View.GONE
                        ll_main_notfound.visibility = View.VISIBLE
                        pb_main.visibility = View.GONE
                    }
                    LoadingType.LOADED -> {
                        rv_main.visibility = View.VISIBLE
                        ll_main_notfound.visibility = View.GONE
                        pb_main.visibility = View.GONE
                    }
                    LoadingType.IS_LOADING -> {
                        rv_main.visibility = View.GONE
                        ll_main_notfound.visibility = View.GONE
                        pb_main.visibility = View.VISIBLE
                    }
                }
            })
        }

    }



}