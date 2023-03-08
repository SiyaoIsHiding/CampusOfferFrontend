package com.example.campusoffer.models.requests


import com.google.gson.annotations.SerializedName

data class NewProduct(
    @SerializedName("category_id")
    val categoryId: String?="",
    @SerializedName("description")
    val description: String?="",
    @SerializedName("_image_num")
    val imageNum: Int?=0,
    @SerializedName("price")
    val price: Double?=0.0,
    @SerializedName("seller_id")
    val sellerId: String?="",
    @SerializedName("title")
    val title: String?=""
)