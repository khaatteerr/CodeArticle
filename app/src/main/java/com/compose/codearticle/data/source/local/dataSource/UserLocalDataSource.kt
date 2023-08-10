package com.compose.codearticle.data.source.local.dataSource

import android.content.SharedPreferences
import com.compose.codearticle.data.utilities.Constants.USER_TOKEN_SHARED_PREFERENCES_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(var prefs : SharedPreferences){
    suspend fun saveUserToken(authorizationKey: String) = withContext(Dispatchers.IO) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN_SHARED_PREFERENCES_KEY, authorizationKey)
        return@withContext editor.commit()
    }

    suspend fun getUserToken(): String? =
        withContext(Dispatchers.IO) {
            prefs.getString(USER_TOKEN_SHARED_PREFERENCES_KEY, null)
        }
}