package com.compose.codearticle.data.utilities

object Constants {
    const val BASE_HTTP_URL = "http://3.121.233.232:4050/api/"
    const val USER_TOKEN_SHARED_PREFERENCES_KEY:String = "UserToken"
    const val USER_SHARED_PREFERENCES_KEY:String = "user"
    val VALID_PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^A-Za-z0-9]).{8,}\$".toRegex()


}