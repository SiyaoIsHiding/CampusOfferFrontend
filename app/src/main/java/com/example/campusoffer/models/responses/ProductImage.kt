package com.example.campusoffer.models.responses

import com.google.gson.annotations.SerializedName

data class ProductImage(
    @SerializedName("image")
    val image : String?
)
