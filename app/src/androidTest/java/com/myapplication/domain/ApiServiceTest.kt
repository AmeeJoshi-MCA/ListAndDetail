package com.myapplication.domain

import com.myapplication.network.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiServiceTest : BaseTest() {

    private lateinit var service: ApiService

    @Before
    fun setup() {
        val url = mockWebServer.url("/")
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(
                GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun list_Api_service() {
        enqueue("response_item.json")
        runBlocking {
            val apiResponse = service.getList()
            val itemList= apiResponse.body()
            if (itemList != null) {
                Assert.assertEquals("The id's did match", "36802",itemList.get(0).id )
            }
        }
    }


}