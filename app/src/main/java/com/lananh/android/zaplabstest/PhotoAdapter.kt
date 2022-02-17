package com.lananh.android.zaplabstest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PhotoAdapter(private val photoDataList: List<PhotoData>):
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val title : TextView = view.findViewById(R.id.title)
        val url: ImageView = view.findViewById(R.id.image)
    }

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.list_item,parent,false)
    return ViewHolder(view)
}

@SuppressLint("SetTextI18n")
override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    holder.title.text = "$position -" + photoDataList[position]
}

override fun getItemCount() = photoDataList.size
}