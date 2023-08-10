package com.compose.codearticle.data.source.remote.endPoints

import com.compose.codearticle.data.source.remote.requestModels.LoginRequestModel
import com.compose.codearticle.data.source.remote.requestModels.RegisterRequestModel
import com.compose.codearticle.data.source.remote.responseModels.BaseApiResponse
import com.compose.codearticle.data.source.remote.responseModels.UserResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApiService {

    @POST("auth/login")
    @Headers("No-Authentication:true")
    suspend fun login(@Body loginBody: LoginRequestModel): Response<BaseApiResponse<UserResponseModel>>

    @POST("users")
    @Headers("No-Authentication:true")
    suspend fun register(@Body registerBody: RegisterRequestModel): Response<BaseApiResponse<UserResponseModel>>
}