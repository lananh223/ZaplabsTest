package com.lananh.android.zaplabstest.model

import com.google.gson.GsonBuilder
import com.lananh.android.zaplabstest.network.DataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class PhotoRepository {
    private val gson = GsonBuilder().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    private val dataService = retrofit.create(DataService::class.java)

    suspend fun getPhotoData(): List<PhotoData> {
        return dataService.getPhotoData()
    }
}