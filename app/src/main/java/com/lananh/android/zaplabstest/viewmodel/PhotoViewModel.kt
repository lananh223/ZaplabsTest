package com.lananh.android.zaplabstest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lananh.android.zaplabstest.model.PhotoData
import com.lananh.android.zaplabstest.model.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoViewModel: ViewModel() {
    private val repository = PhotoRepository()

    private var _photoLiveDataList: MutableLiveData<List<PhotoData>> = MutableLiveData()
    val photoLiveDataList: LiveData<List<PhotoData>>  = _photoLiveDataList

    fun getDataFromRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            _photoLiveDataList.postValue(repository.getPhotoData())
        }
    }
}