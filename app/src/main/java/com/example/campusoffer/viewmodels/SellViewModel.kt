package com.example.campusoffer.viewmodels

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.campusoffer.data.ProductRepository
import com.example.campusoffer.models.Product
import com.example.campusoffer.models.requests.NewProduct
import com.example.campusoffer.models.responses.ImageIdList
import com.example.campusoffer.util.Constants.Companion.CATEGORY_ROOT_ID
import com.example.campusoffer.util.Constants.Companion.USER_TEST_ID
import com.example.campusoffer.util.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response

class SellViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    application: Application
) : AndroidViewModel(application) {

    private val TAG = "SellViewModel"
    val postProductRes: MutableLiveData<NetworkResult<ImageIdList>> = MutableLiveData()
    fun postNewProduct(
        title: String,
        description: String,
        price: Double
    ){
        val body = NewProduct(CATEGORY_ROOT_ID, description, 0, price, USER_TEST_ID, title)
        viewModelScope.launch {
            val res = productRepository.remote.postNewProduct(body)
            Log.v(TAG, res.body()?.idList.toString())
            postProductRes.value = handleNewProductResponse(res)
        }

    }

    private fun handleNewProductResponse(res: Response<ImageIdList>): NetworkResult<ImageIdList> {

        when {
            res.code() >= 500 -> {
                return NetworkResult.Error("Server Internal Error")
            }

            res.code() >= 400 -> {
                return NetworkResult.Error("Server Error")
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