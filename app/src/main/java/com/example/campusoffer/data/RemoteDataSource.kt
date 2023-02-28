package com.example.campusoffer.data

import android.util.Log
import com.example.campusoffer.data.network.CampusOfferApi
import com.example.campusoffer.models.Category
import com.example.campusoffer.models.Product
import com.example.campusoffer.models.User
import com.example.campusoffer.models.responses.ProductsIdList
import com.example.campusoffer.models.responses.SubCategory
import com.example.campusoffer.util.Constants.Companion.QUERY_CATEGORY_ID
import com.example.campusoffer.util.Constants.Companion.QUERY_ID
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val campusOfferApi: CampusOfferApi
){
    private val DEBUG_TAG = "RemoteDataSource"
    suspend fun getProductsUnderCategory(queries: Map<String, String>): Response<ProductsIdList>{
        if (!queries.containsKey(QUERY_CATEGORY_ID)) {
            Log.e(DEBUG_TAG, "Mandatory queries parameter not found")
        }
        return campusOfferApi.getProductsUnderCategory(queries)
    }

    suspend fun getProductByID(queries: Map<String, String> ): Response<Product>{
        if (queries.containsKey(QUERY_ID)){
            Log.e(DEBUG_TAG, "Mandatory queries parameter not found")
        }
        return campusOfferApi.getProductByID(queries)
    }

    suspend fun getSubCategory(queries: Map<String, String> ): Response<SubCategory>{
        if (queries.containsKey(QUERY_ID)){
            Log.e(DEBUG_TAG, "Mandatory queries parameter not found")
        }
        return campusOfferApi.getSubCategory(queries)
    }

    suspend fun getCategoryByID(queries: Map<String, String>): Response<Category>{
        if (queries.containsKey(QUERY_ID)){
            Log.e(DEBUG_TAG, "Mandatory queries parameter not found")
        }
        return campusOfferApi.getCategoryByID(queries)
    }

    suspend fun getUserByID(queries: Map<String, String>) : Response<User>{
        if (queries.containsKey(QUERY_ID)){
            Log.e(DEBUG_TAG, "Mandatory queries parameter not found")
        }
        return campusOfferApi.getUserByID(queries)
    }
}