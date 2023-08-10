package com.compose.codearticle.data.source.remote.responseModels

import com.compose.codearticle.domain.models.UserModel
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class UserResponseModel(
//    @Json(name = "id")
//    val userId:String,
    @SerializedName("token")
    val userToken:String,
    @SerializedName("username")
    val userName:String,
//    @Json(name = "email")
//    val email:String
){
    fun toUserModel(): UserModel = UserModel(token = userToken, email = userName)
}
