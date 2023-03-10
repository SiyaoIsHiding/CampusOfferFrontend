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

class FavoriteViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    application: Application
): AndroidViewModel(application){

    var favoritesList : MutableLiveData<MutableList<Product?>> = MutableLiveData()
    var coverImageList: MutableLiveData<MutableList<Drawable?>> = MutableLiveData(mutableListOf())

    private val TAG = "FavoriteViewModel"
    fun getProductsList(queries: Map<String, String>) = viewModelScope.launch{
        productRepository.getSavedProducts(queries, favoritesList, coverImageList){ind, product -> //TODO: change to saved products
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

    fun insertFavoriteProduct( product: Product) {
        favoritesList.value?.toMutableList()?.add(product)
        favoritesList.value = favoritesList.value
    }

    fun getCurrentFavoritesList() : List<Product?>? {
        return favoritesList.value
    }
}