package com.example.campusoffer.models.responses

import com.example.campusoffer.models.Category
import com.google.gson.annotations.SerializedName

data class SubCategory(
    @SerializedName("subcategory")
    val subcategory : List<Category>
)
