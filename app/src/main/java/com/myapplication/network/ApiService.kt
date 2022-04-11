package com.myapplication.network


import com.myapplication.network.model.RestApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "http://mobcategories.s3-website-eu-west-1.amazonaws.com"
    }

    @GET("/")
    suspend fun getList(): Response<RestApiResponse>

}