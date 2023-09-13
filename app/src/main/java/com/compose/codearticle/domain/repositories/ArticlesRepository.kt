package com.compose.codearticle.domain.repositories

import androidx.paging.PagingData
import com.compose.codearticle.domain.models.ArticleModel
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    suspend fun getAllArticles(): Flow<PagingData<ArticleModel>>
}