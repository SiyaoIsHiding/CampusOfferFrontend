package com.example.campusoffer.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.campusoffer.data.ProductRepository
import com.example.campusoffer.models.Product
import com.example.campusoffer.models.responses.ProductsIdList
import com.example.campusoffer.util.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response


class ShopViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    application: Application
): AndroidViewModel(application){

    var productsList : MutableLiveData<MutableList<Product?>> = MutableLiveData(mutableListOf())
    var coverImageList: MutableLiveData<MutableList<Bitmap?>> = MutableLiveData(mutableListOf())

    val TAG = "ShopViewModel"

    fun getProductsList(queries: Map<String, String>) = viewModelScope.launch{
        productRepository.getListProducts(queries, productsList, coverImageList) {ind, product ->
            if (!product?._images.isNullOrEmpty()){
                viewModelScope.launch {
                    val byteArray = productRepository.getImageBytesById(product!!._images!!.get(0))
                    if (byteArray != null){
                        Log.v(TAG, "byteArray not null")
                        val decodedImage = BitmapFactory.decodeByteArray(byteArray, 0 , byteArray.size)
                        coverImageList.value!!.set(ind, decodedImage)
                        coverImageList.value = coverImageList.value
                    }
                }
            }
        }
    }

    var productsUnderCategoryRes: MutableLiveData<NetworkResult<ProductsIdList>> = MutableLiveData()

    fun getProductsUnderCategory(queries: Map<String, String>) = viewModelScope.launch {
        getProductsUnderCategorySafeCall(queries)
    }

    private suspend fun getProductsUnderCategorySafeCall(queries: Map<String, String>) {
        productsUnderCategoryRes.value = NetworkResult.Loading()

        try {
            val res = productRepository.remote.getProductsUnderCategory(queries)
            productsUnderCategoryRes.value = handleProductsUnderCategoryResponse(res)
        } catch (e: Exception) {
            productsUnderCategoryRes.value = NetworkResult.Error("No Internet Connection.")
        }

    }

    private fun handleProductsUnderCategoryResponse(res: Response<ProductsIdList>): NetworkResult<ProductsIdList> {
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