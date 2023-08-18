package com.compose.codearticle.domain.repositories

import com.compose.codearticle.domain.models.ArticleModel

interface ArticlesRepository {
    suspend fun getAllArticles(pageNumber:Int): List<ArticleModel>
}