package com.example.campusoffer.viewmodels

import android.app.Application
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

    var productsList : MutableLiveData<List<Product?>> = MutableLiveData()

    fun getProductsList(queries: Map<String, String>) = viewModelScope.launch{
        productRepository.getListProducts(queries, productsList)
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


//    fun applyHardCodeData() : List<Product> {
//
//        val image = listOf<String>(
//            "b8884ed5-b0de-11ed-a0a9-00224829ee55",
//            "c514d4b9-b0de-11ed-a0a9-00224829ee55"
//        )
//        val product1: Product = Product(
//            "41859207-5471-4223-b01c-e566d506c799",
//            "0301",
//            "A fully working chair. Bought in March last year.",
//            "92c6ebb6-b0ca-11ed-a0a9-00224829ee55",
//            image,
//            0,
//            29.9,
//            "fcda1dda-5b3b-4c6c-88a7-46521d132015",
//            "An office chair at Verano Place"
//        )
//        val product2: Product = Product(
//            "41859207-5471-4223-b01c-e566d506c799",
//            "0301",
//            "A fully working chair. Bought in March last year.",
//            "92c6ebb6-b0ca-11ed-a0a9-00224829ee55",
//            image,
//            1,
//            29.9,
//            "fcda1dda-5b3b-4c6c-88a7-46521d132015",
//            "An office chair at Verano Place"
//        )
//        return listOf(product1, product2, product1, product1)
//    }
}