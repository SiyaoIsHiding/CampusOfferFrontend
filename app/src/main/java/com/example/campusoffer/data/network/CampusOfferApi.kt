package com.example.campusoffer.data.network

import com.example.campusoffer.models.Product
import com.example.campusoffer.models.responses.ProductsIdList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CampusOfferApi {

    /**
     * {{host}}/products?category_id=699387fd-b0e9-11ed-a0a9-00224829ee55
     */
    @GET("/products")
    suspend fun getProductsUnderCategory(
        @QueryMap queries: Map<String, String>
    ): Response<ProductsIdList>

    /**
     * {{host}}/product?id=92c6ebb6-b0ca-11ed-a0a9-00224829ee55
     */
    @GET("/product")
    suspend fun getProductByID(
        @QueryMap queries: Map<String, String>
    ): Response<Product>
}