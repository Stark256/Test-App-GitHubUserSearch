package com.features.test_app_githubusersearch.app.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.features.test_app_githubusersearch.api.ApiService
import com.features.test_app_githubusersearch.api.models.RepoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(private val api: ApiService) : ViewModel() {

    val repos = MutableLiveData<ArrayList<RepoModel>>()



    fun loadRepos(login: String) {

        api.getUserRepos(login)
            .enqueue(object : Callback<ArrayList<RepoModel>> {
                override fun onFailure(call: Call<ArrayList<RepoModel>>, t: Throwable) {}

                override fun onResponse(
                    call: Call<ArrayList<RepoModel>>,
                    response: Response<ArrayList<RepoModel>>
                ) {
                    response.body()?.let {
                       repos.value = it
                    }
                }
            })

    }

}