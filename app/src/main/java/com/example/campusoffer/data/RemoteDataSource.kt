package com.example.campusoffer.data

import android.util.Log
import com.example.campusoffer.data.network.CampusOfferApi
import com.example.campusoffer.models.responses.ProductsUnderCategory
import com.example.campusoffer.util.Constants.Companion.QUERY_CATEGORY_ID
import retrofit2.Response
import retrofit2.http.QueryMap
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val campusOfferApi: CampusOfferApi
){
    private val DEBUG_TAG = "RemoteDataSource"
    suspend fun getProductUnderCategory(queries: Map<String, String>): Response<ProductsUnderCategory>{
        if (!queries.containsKey(QUERY_CATEGORY_ID)) {
            Log.e(DEBUG_TAG, "Mandatory queries parameter not found")
        }
        return campusOfferApi.getProductUnderCategory(queries)
    }
}