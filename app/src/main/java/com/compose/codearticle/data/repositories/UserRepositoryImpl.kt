package com.compose.codearticle.data.repositories

import com.compose.codearticle.data.source.local.dataSource.UserLocalDataSource
import com.compose.codearticle.data.source.remote.dataSource.UserRemoteDataSource
import com.compose.codearticle.data.source.remote.requestModels.LoginRequestModel
import com.compose.codearticle.data.source.remote.requestModels.RegisterRequestModel
import com.compose.codearticle.data.utilities.isDataHasGotSuccessfully
import com.compose.codearticle.domain.models.UserModel
import com.compose.codearticle.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val externalScope: CoroutineScope
) : UserRepository {
    override suspend fun login(email: String, password: String): UserModel {
        return externalScope.async {
            userRemoteDataSource.login(LoginRequestModel(email, password))
                .also {
                    if (it.isDataHasGotSuccessfully()) {
                        userLocalDataSource.saveUserToken(it.body()!!.posts!!.userToken)
                    } else throw Exception(it.body()!!.error)
                }
        }.await().body()!!.posts!!.toUserModel()
    }

    override suspend fun register(
         userName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): UserModel {
        return externalScope.async {
            userRemoteDataSource.register(
                RegisterRequestModel(

                    username = userName,
                    email = email,
                    password = password,
                    passwordConfirmation = passwordConfirmation
                )
            )
                .also {
                    if (it.isDataHasGotSuccessfully()) {
                        userLocalDataSource.saveUserToken(it.body()!!.posts!!.userToken)
                    } else throw Exception(it.body()!!.error)
                }
        }.await().body()!!.posts!!.toUserModel()
    }

    override suspend fun getUserToken(): String? {
        return userLocalDataSource.getUserToken()
    }
}