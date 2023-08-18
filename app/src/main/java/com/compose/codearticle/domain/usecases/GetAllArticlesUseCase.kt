package com.compose.codearticle.domain.usecases

import com.compose.codearticle.domain.models.ArticleModel
import com.compose.codearticle.domain.repositories.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllArticlesUseCase @Inject constructor(
    private val articlesRepository: ArticlesRepository
) {
    private var allArticles: List<ArticleModel> = emptyList()

    suspend operator fun invoke(pageNumber: Int): Flow<List<ArticleModel>> {
        return flow {
            allArticles = articlesRepository.getAllArticles(pageNumber)
            emit(allArticles)
        }
    }

}