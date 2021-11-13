package com.example.mobilework

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkingGitHub {

    @GET("users/{user}")
    fun getUser(@Path("user") user: String?): Call<User>

    @GET("repos/{user}/{repos_name}")
    fun getRepos(@Path("user") user: String?, @Path("repos_name") repos: String?): Call<Repos>
}

