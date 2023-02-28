package com.example.campusoffer.models.responses

import com.google.gson.annotations.SerializedName

data class ProductsIdList(
    @SerializedName("product_id")
    val productId: List<String>
)
