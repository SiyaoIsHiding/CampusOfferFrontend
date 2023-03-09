package com.example.campusoffer.models


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("bio")
    var bio: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("id")
    val id: String?,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("email")
    val email: String
)