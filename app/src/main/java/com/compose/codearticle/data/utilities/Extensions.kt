package com.compose.codearticle.data.utilities

import com.compose.codearticle.data.source.remote.responseModels.BaseApiResponse
import com.compose.codearticle.domain.models.ValidateResult
import org.json.JSONObject
import retrofit2.Response

fun<T> Response<T>.isDataHasGotSuccessfully() = isSuccessful && body() != null &&  code() == 200
fun ValidateResult.isFieldDataValid() = error == null
fun<T> Response<BaseApiResponse<T>>.getErrorMessageFromResponse(): String = JSONObject(errorBody()!!.string()).getString("message")