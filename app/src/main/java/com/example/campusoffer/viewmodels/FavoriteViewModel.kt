package com.example.campusoffer.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.campusoffer.R
import com.example.campusoffer.data.ProductRepository
import com.example.campusoffer.models.Product
import kotlinx.coroutines.launch

class FavoriteViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    application: Application
): AndroidViewModel(application){

    var favoritesList : MutableLiveData<List<Product?>> = MutableLiveData()

    fun getProductsList(queries: Map<String, String>) = viewModelScope.launch{
        productRepository.getListProducts(queries, favoritesList)
    }

    fun insertFavoriteProduct( product: Product) {
        favoritesList.value?.toMutableList()?.add(product)
        favoritesList.value = favoritesList.value
    }

    fun getCurrentFavoritesList() : List<Product?>? {
        return favoritesList.value
    }
}