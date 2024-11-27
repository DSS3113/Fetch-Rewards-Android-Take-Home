package com.example.fetchTakeHome
import retrofit2.Response
import retrofit2.http.GET

interface ItemApi {

    @GET("hiring.json")
    suspend fun getItems(): Response<List<Item>>

}