package com.features.test_app_githubusersearch.api

import com.features.test_app_githubusersearch.api.models.RepoModel
import com.features.test_app_githubusersearch.api.models.UserDataModel
import com.features.test_app_githubusersearch.api.models.UsersResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
    fun getUsers(
        @Query("q") q: String
    ): Call<UsersResponseModel>

    @GET("/users/{login}")
    fun getUserDetails(
        @Path("login") login: String
    ): Call<UserDataModel>

    @GET("/users/{login}/repos")
    fun getUserRepos(
        @Path("login") login: String
    ): Call<ArrayList<RepoModel>>
}