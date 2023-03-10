package com.example.campusoffer.viewmodels

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.campusoffer.data.ProductRepository
import com.example.campusoffer.models.Product
import kotlinx.coroutines.launch


class ShopViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    application: Application
): AndroidViewModel(application){

    var productsList : MutableLiveData<MutableList<Product?>> = MutableLiveData(mutableListOf())
    var coverImageList: MutableLiveData<MutableList<Drawable?>> = MutableLiveData(mutableListOf())

    val TAG = "ShopViewModel"

    fun getProductsList(queries: Map<String, String>) = viewModelScope.launch{
        productRepository.getListProducts(queries, productsList, coverImageList) {ind, product ->
            if (!product?._images.isNullOrEmpty()){
                viewModelScope.launch {
                    val drawable = productRepository.getImageBytesById(product!!._images!!.get(0))
                    if (drawable != null){
                        coverImageList.value!!.set(ind, drawable)
                        coverImageList.value = coverImageList.value
                    }
                }
            }
        }
    }


}