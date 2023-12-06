package com.lananh.android.zaplabstest.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lananh.android.zaplabstest.R
import com.lananh.android.zaplabstest.model.PhotoData

class PhotoAdapter(var photoDataList: List<PhotoData>) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val image: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photoData = photoDataList[position]
        holder.title.text = " $position - " + photoData.title
        Glide.with(holder.itemView)
            .load(photoData.url)
            .into(holder.image)
    }

    override fun getItemCount() = photoDataList.size
}