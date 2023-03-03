package com.example.campusoffer.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("create_date")
    val createDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("_images")
    val _images: List<String>,
    @SerializedName("is_sold")
    val isSold: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("seller_id")
    val sellerId: String,
    @SerializedName("title")
    val title: String
) : Parcelable