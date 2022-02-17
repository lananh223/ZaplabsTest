package com.lananh.android.zaplabstest

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"
private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var photoDataList : List<PhotoData>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Using retrofit
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val dataService = retrofit.create(DataService::class.java)

        dataService.getPhotoData().enqueue(object: Callback<List<PhotoData>>{
            override fun onResponse(
                call: Call<List<PhotoData>>,
                response: Response<List<PhotoData>>
            ) {
                Log.i(com.lananh.android.zaplabstest.TAG, "onResponse $response")

                photoDataList = response.body()
                if (photoDataList == null){
                    Log.w(TAG, "Did not receive a valid response body")
                    return
                }

                recyclerView.adapter = PhotoAdapter(photoDataList!!)
                    .apply {
                        notifyDataSetChanged()
                    }
            }

            override fun onFailure(call: Call<List<PhotoData>>, t: Throwable) {
                Log.e(TAG, "onFailure $t")
            }
        })

        //RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
        //set layout manager to new instance
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    inner class PhotoAdapter(private val photoDataList: List<PhotoData>):
        RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){
        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
            val title : TextView = view.findViewById(R.id.title)
            val imageView: ImageView = view.findViewById(R.id.image)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item,parent,false)
            return ViewHolder(view)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            //need to use GlideUrl, User-Agent header parameter for the link: https://via.placeholder.com
            val glideUrl = GlideUrl(
                photoDataList[position].url,
                LazyHeaders.Builder()
                    .addHeader("User-Agent", "5")
                    .build()
            )

            Glide.with(holder.itemView)
                .load(glideUrl)
                .into(holder.imageView)

            holder.title.text = photoDataList[position].id.toString() + " - " + photoDataList[position].title
        }

        override fun getItemCount() = photoDataList.size
    }
}
