package com.example.campusoffer.data.network

import com.example.campusoffer.models.responses.ProductsUnderCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CampusOfferApi {

    /**
     * {{host}}/products?category_id=699387fd-b0e9-11ed-a0a9-00224829ee55
     */
    @GET("/products")
    suspend fun getProductUnderCategory(
        @QueryMap queries: Map<String, String>
    ): Response<ProductsUnderCategory>
}