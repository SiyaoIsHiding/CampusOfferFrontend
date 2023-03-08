package com.example.campusoffer.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.campusoffer.data.ProductRepository
import com.example.campusoffer.models.Product
import kotlinx.coroutines.launch

class OverviewViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    application: Application
) : AndroidViewModel(application) {
    var imageBitmap : MutableLiveData<Bitmap> = MutableLiveData()

    fun requestCoverImage(product: Product){
        if (!product._images.isNullOrEmpty()){
            viewModelScope.launch {
                val byteArray = productRepository.getImageBytesById(product._images?.get(0))
                if (byteArray != null){
                    val decodedImage = BitmapFactory.decodeByteArray(byteArray, 0 , byteArray.size)
                    imageBitmap.value = decodedImage
                }
            }
        }
    }
}