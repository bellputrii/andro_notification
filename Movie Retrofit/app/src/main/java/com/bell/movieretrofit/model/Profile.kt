package com.bell.movieretrofit.model

import com.google.gson.annotations.SerializedName

// mendefinisikan kelas data (data class) bernama DataCallback yang digunakan sebagai model
// untuk merepresentasikan data yang diterima dari respons API dalam bentuk objek.
data class Profile(
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val per_page: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_page")
    val total_page: Int,
    @SerializedName("data")
    val data: List<Data>,
)