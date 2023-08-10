package com.compose.codearticle.data.source.remote.dataSource

import com.compose.codearticle.data.source.remote.endPoints.UserApiService
import com.compose.codearticle.data.source.remote.requestModels.LoginRequestModel
import com.compose.codearticle.data.source.remote.requestModels.RegisterRequestModel
import com.compose.codearticle.data.source.remote.responseModels.BaseApiResponse
import com.compose.codearticle.data.source.remote.responseModels.UserResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userApiService: UserApiService) {

    suspend fun login(loginRequestModel: LoginRequestModel): Response<BaseApiResponse<UserResponseModel>> =
        withContext(Dispatchers.IO) {
            userApiService.login(loginBody = loginRequestModel)
        }

    suspend fun register(registerRequestModel: RegisterRequestModel): Response<BaseApiResponse<UserResponseModel>> =
        withContext(Dispatchers.IO) {
            userApiService.register(registerBody = registerRequestModel)
        }
}