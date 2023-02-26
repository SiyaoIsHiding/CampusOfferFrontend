package com.example.campusoffer.models.responses

import com.google.gson.annotations.SerializedName

data class ProductsUnderCategory(
    @SerializedName("product_id")
    val productId: List<String>
)
