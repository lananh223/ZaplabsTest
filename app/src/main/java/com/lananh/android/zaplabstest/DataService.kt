package com.lananh.android.zaplabstest

import retrofit2.Call
import retrofit2.http.GET

interface DataService {
    @GET("albums/1/photos")
    fun getPhotoData(): Call<List<PhotoData>>
}