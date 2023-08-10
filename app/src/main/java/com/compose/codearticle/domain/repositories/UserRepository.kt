package com.compose.codearticle.domain.repositories

import com.compose.codearticle.domain.models.UserModel

interface UserRepository {

    suspend fun login(email: String, password: String): UserModel
    suspend fun register(
        name: String,
        userName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ):UserModel
    suspend fun getUserToken(): String?

}