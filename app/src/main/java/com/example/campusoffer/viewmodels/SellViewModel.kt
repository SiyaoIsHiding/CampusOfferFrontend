package com.example.campusoffer.viewmodels

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.campusoffer.data.ProductRepository
import com.example.campusoffer.models.Product
import com.example.campusoffer.models.requests.NewProduct
import com.example.campusoffer.util.Constants.Companion.CATEGORY_ROOT_ID
import com.example.campusoffer.util.Constants.Companion.USER_TEST_ID
import kotlinx.coroutines.launch

class SellViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    application: Application
): AndroidViewModel(application){

    private val TAG = "SellViewModel"
    fun postNewProduct(title: String, description: String, price: Double){
        val body = NewProduct(CATEGORY_ROOT_ID, description, 0, price, USER_TEST_ID, title)
        viewModelScope.launch {
            val res = productRepository.remote.postNewProduct(body)
            Log.v(TAG, res.body()?.idList.toString())
        }

    }


}