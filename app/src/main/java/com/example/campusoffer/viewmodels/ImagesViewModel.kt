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

class ImagesViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    application: Application
): AndroidViewModel(application){

    var imageBitmapList : MutableLiveData<MutableList<Bitmap?>> = MutableLiveData(mutableListOf())

    fun requestImage(product: Product) {
        if (!product._images.isNullOrEmpty()){
            imageBitmapList.value = MutableList(product._images.size) { index ->  null}
            for (i in product._images?.indices){
                viewModelScope.launch {
                    val byteArray = productRepository.getImageBytesById(product._images?.get(i))
                    if (byteArray != null){
                        val decodedImage = BitmapFactory.decodeByteArray(byteArray, 0 , byteArray.size)
                        imageBitmapList.value?.set(i, decodedImage)
                        imageBitmapList.value = imageBitmapList.value
                    }
                }
            }
        }

    }

}