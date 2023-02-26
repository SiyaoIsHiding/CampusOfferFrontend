package com.example.campusoffer.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.campusoffer.data.ProductRepository
import com.example.campusoffer.models.responses.ProductsUnderCategory
import com.example.campusoffer.util.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileViewModel @ViewModelInject constructor(
    private val repository: ProductRepository,
    application: Application) : AndroidViewModel(application) {

    var response: MutableLiveData<NetworkResult<ProductsUnderCategory>> = MutableLiveData()

    fun getProductUnderCategory(queries: Map<String, String>) = viewModelScope.launch {
        getProductUnderCategorySafeCall(queries)
    }

    private suspend fun getProductUnderCategorySafeCall(queries: Map<String, String>){
        response.value = NetworkResult.Loading()

        try {
            val res = repository.remote.getProductUnderCategory(queries)
            response.value = handleProductUnderCategoryResponse(res)
        }catch (e: Exception){
            response.value = NetworkResult.Error("No Internet Connection.")
        }

    }

    private fun handleProductUnderCategoryResponse(res: Response<ProductsUnderCategory>): NetworkResult<ProductsUnderCategory>{
        when {
            res.code() >= 500 -> {
                return NetworkResult.Error("Server Internal Error")
            }

            res.code() >= 400 -> {
                return NetworkResult.Error("Server Error")
            }

            res.body()!!.productId.isEmpty() -> {
                return NetworkResult.Error("No product under this category")
            }

            res.isSuccessful -> {
                return NetworkResult.Success(res.body()!!)
            }

            else -> {
                return NetworkResult.Error(res.message())
            }
        }
    }
}