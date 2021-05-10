package com.example.myapplication.backend

import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("users/{user}/repos")
    fun getRepositoriesForUsers(@Path("user") username:String)
}