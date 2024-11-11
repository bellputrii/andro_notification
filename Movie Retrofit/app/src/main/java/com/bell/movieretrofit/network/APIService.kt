package com.bell.movieretrofit.network

import com.bell.movieretrofit.model.Profile
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("users?page=2")
    fun getAllUsers(): Call<Profile>

}