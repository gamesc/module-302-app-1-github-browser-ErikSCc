package com.example.myapplication.backend

import com.example.myapplication.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("users/{user}/repos")
    fun getRepositoriesForUsers(@Path("user") username:String): Call<List<Repository>>
}