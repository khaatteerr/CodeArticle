//package com.compose.codearticle.data.mapper
//
//import androidx.room.TypeConverter
//import com.compose.codearticle.data.source.local.database.ArticleDao
//import com.compose.codearticle.data.source.local.database.ArticleEntity
//import com.compose.codearticle.data.source.remote.responseModels.ArticleResponseModel
//import com.compose.codearticle.domain.models.ArticleModel
//
//fun ArticleResponseModel.toArticleEntity(): ArticleEntity {
//    return ArticleEntity(
//        id = id,
//        title = title,
//        liked = liked,
//        attachment = attachment,
//        commentsCount = commentsCount,
//        content = content,
//        user = user
//
//    )
//
//}
//
// fun ArticleEntity.toArticleModel(): ArticleModel {
//    return ArticleModel(
//        id = id,
//        title = title,
//        liked = liked,
//        attachment = attachment!!,
//        commentsCount = commentsCount!!,
//        content = content,
//        user = user!!
//    )
//}