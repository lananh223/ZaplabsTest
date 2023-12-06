package com.lananh.android.zaplabstest.network

import com.lananh.android.zaplabstest.model.PhotoData
import retrofit2.http.GET

interface DataService {
    @GET("albums/1/photos")
    suspend fun getPhotoData(): List<PhotoData>
}