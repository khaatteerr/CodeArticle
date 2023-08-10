package com.compose.codearticle.data.source.remote.requestModels

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class LoginRequestModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
