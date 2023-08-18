package com.compose.codearticle.data.source.remote.responseModels

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class BaseApiResponse<T>(var error: String?, var posts: T?)


