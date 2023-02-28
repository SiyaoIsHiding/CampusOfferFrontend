package com.example.campusoffer.data

import android.util.Log
import com.example.campusoffer.data.network.CampusOfferApi
import com.example.campusoffer.models.Product
import com.example.campusoffer.models.responses.ProductsIdList
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
}