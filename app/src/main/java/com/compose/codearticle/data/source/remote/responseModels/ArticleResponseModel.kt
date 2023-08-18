package com.compose.codearticle.data.source.remote.responseModels

import com.compose.codearticle.domain.models.ArticleModel
import com.google.gson.annotations.SerializedName

data class ArticleResponseModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("liked") val liked: Boolean,
    @SerializedName("attachment") val attachment: Attachment,
    @SerializedName("user") val user: User,
    @SerializedName("comments_count") val commentsCount: Int

) {
    fun toArticleModel() = ArticleModel(
        id = id,
        title = title,
        content = content,
        liked = liked,
        attachment = attachment,
        user = user,
        commentsCount = commentsCount
    )
}

data class Attachment(
    @SerializedName("image") val image: String,
    @SerializedName("image_width") val imageWidth: String,
    @SerializedName("image_height") val imageHeight: String,

    )

data class User(@SerializedName("user_name") val userName: String)
