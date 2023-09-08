package com.compose.codearticle.domain.usecases

import androidx.paging.PagingData
import com.compose.codearticle.domain.models.ArticleModel
import com.compose.codearticle.domain.repositories.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllArticlesUseCase @Inject constructor(
    private val articlesRepository: ArticlesRepository
) {

    suspend operator fun invoke(): Flow<PagingData<ArticleModel>> {
        return  articlesRepository.getAllArticles()

    }

}