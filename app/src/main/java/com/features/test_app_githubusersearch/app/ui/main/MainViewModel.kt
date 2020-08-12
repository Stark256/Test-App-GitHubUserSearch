package com.features.test_app_githubusersearch.app.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.features.test_app_githubusersearch.api.ApiService
import com.features.test_app_githubusersearch.api.models.UserDataModel
import com.features.test_app_githubusersearch.api.models.UserModel
import com.features.test_app_githubusersearch.api.models.UsersResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val api: ApiService) : ViewModel() {

    val users = MutableLiveData<ArrayList<UserDataModel>>()

    val isLoaded = MutableLiveData<LoadingType>()


    fun searchUsers(searchString: String?) {
        if(!searchString.isNullOrBlank()) {
            isLoaded.value = LoadingType.IS_LOADING
            api.getUsers(searchString).enqueue(object : Callback<UsersResponseModel> {
                override fun onFailure(call: Call<UsersResponseModel>, t: Throwable) {
                    isLoaded.value = LoadingType.ERROR
                }

                override fun onResponse(
                    call: Call<UsersResponseModel>,
                    response: Response<UsersResponseModel>
                ) {
                    if (response.body() != null && response.body()?.items?.isNotEmpty() ?: false) {
                        val arrResult = ArrayList<UserDataModel>()
                        if (response.body() != null) {

                            response.body()?.let { usersResponseModel ->
                                for (itemUser in usersResponseModel.items.take(5)) {

                                    api.getUserDetails(itemUser.login)
                                        .enqueue(object : Callback<UserDataModel> {
                                            override fun onFailure(call: Call<UserDataModel>, t: Throwable) {}

                                            override fun onResponse(
                                                call: Call<UserDataModel>,
                                                response: Response<UserDataModel>
                                            ) {
                                                response.body()?.let {
                                                    arrResult.add(it)

                                                    if(usersResponseModel.items.size < 5 && usersResponseModel.items.size == arrResult.size || arrResult.size == 5) {
                                                        users.value = arrResult
                                                        isLoaded.value = LoadingType.LOADED
                                                    }
                                                }
                                            }
                                        })
                                }

                            }

                        } else {
                            isLoaded.value = LoadingType.ERROR
                        }


                    } else {
                        isLoaded.value = LoadingType.ERROR
                    }
                }
            })
        }

    }

}