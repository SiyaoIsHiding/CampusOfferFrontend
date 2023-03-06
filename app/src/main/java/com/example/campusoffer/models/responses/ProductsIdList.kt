package com.example.campusoffer.models.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsIdList(
    @SerializedName("product_id")
    val productId: List<String>
): Parcelable
