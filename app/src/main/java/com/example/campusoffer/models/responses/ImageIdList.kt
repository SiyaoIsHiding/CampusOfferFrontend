package com.example.campusoffer.models.responses

import com.google.gson.annotations.SerializedName

data class ImageIdList(
    @SerializedName("_images")
    val idList : List<String>
)
