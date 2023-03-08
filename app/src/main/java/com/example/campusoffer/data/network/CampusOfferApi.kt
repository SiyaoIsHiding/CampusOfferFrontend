package com.example.campusoffer.data.network

import com.example.campusoffer.models.Category
import com.example.campusoffer.models.Product
import com.example.campusoffer.models.User
import com.example.campusoffer.models.requests.NewProduct
import com.example.campusoffer.models.responses.ImageIdList
import com.example.campusoffer.models.responses.SingleImage
import com.example.campusoffer.models.responses.ProductsIdList
import com.example.campusoffer.models.responses.SubCategory
import retrofit2.Response
import retrofit2.http.*

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

    /**
     * {{host}}/subcategory?id=00000000-0000-0000-0000-00000000000
     */
    @GET("/subcategory")
    suspend fun getSubCategory(
        @QueryMap queries: Map<String, String>
    ): Response<SubCategory>

    /**
     * {{host}}/usr?id=d16860a0-b01e-11ed-a0a9-00224829ee55
     */
    @GET("/usr")
    suspend fun getUserByID(
        @QueryMap queries: Map<String, String>
    ): Response<User>

    /**
     * {{host}}/category?id=41859207-5471-4223-b01c-e566d506c799
     */
    @GET("/category")
    suspend fun getCategoryByID(
        @QueryMap queries: Map<String, String>
    ): Response<Category>

    @GET("/images/{imageId}")
    suspend fun getImageByID(
        @Path("imageId") id: String
    ): Response<SingleImage>

    @POST("/products")
    suspend fun postProduct(
        @Body product: NewProduct
    ): Response<ImageIdList>
}