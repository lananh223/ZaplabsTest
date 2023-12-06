package com.lananh.android.zaplabstest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lananh.android.zaplabstest.R
import com.lananh.android.zaplabstest.viewmodel.PhotoViewModel

class PhotoFragment : Fragment() {

    private val photoAdapter = PhotoAdapter(emptyList())
    private val viewModel: PhotoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set Recyclerview layout manager
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = photoAdapter

        // Observe livedata in fragment
        viewModel.photoLiveDataList.observe(viewLifecycleOwner) { result ->
            photoAdapter.photoDataList = result
            photoAdapter.notifyDataSetChanged()
        }
        viewModel.getDataFromRepository()
    }
}