package com.example.campusoffer.models.responses

import com.google.gson.annotations.SerializedName

data class SavedProducts(
    @SerializedName("saved_products")
    val savedProducts: List<String>
)
