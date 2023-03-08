package com.example.campusoffer.models.responses

import com.google.gson.annotations.SerializedName

data class SingleImage(
    @SerializedName("image")
    val image : String?
)
