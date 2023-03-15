package com.example.campusoffer.viewmodels

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.campusoffer.data.ProductRepository
import com.example.campusoffer.models.Product
import com.example.campusoffer.models.User
import kotlinx.coroutines.launch

class OverviewViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    application: Application
) : AndroidViewModel(application) {
    var imageBitmap : MutableLiveData<Drawable> = MutableLiveData()

    fun requestCoverImage(product: Product){
        if (!product._images.isNullOrEmpty()){
            viewModelScope.launch {
                val drawable = productRepository.getImageBytesById(product._images?.get(0))
                if (drawable != null){
                    imageBitmap.value = drawable
                }
            }
        }
    }


    var currentUser : MutableLiveData<User?> = MutableLiveData()

    fun getUserProfile(queries: Map<String, String>) = viewModelScope.launch {
        val res = productRepository.remote.getUserByID(queries)
        if(res.isSuccessful && res.body() != null){
            currentUser.postValue(res.body()!!)
        }
    }
}