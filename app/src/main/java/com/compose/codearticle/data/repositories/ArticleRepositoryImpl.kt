package com.compose.codearticle.data.repositories

import com.compose.codearticle.data.source.remote.dataSource.ArticleRemoteDataSource
import com.compose.codearticle.data.utilities.getErrorMessageFromResponse
import com.compose.codearticle.data.utilities.isDataHasGotSuccessfully
import com.compose.codearticle.domain.models.ArticleModel
import com.compose.codearticle.domain.repositories.ArticlesRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleRemoteDataSource: ArticleRemoteDataSource
) : ArticlesRepository {
    override suspend fun getAllArticles(pageNumber: Int): List<ArticleModel> {
        val getArticlesResult = articleRemoteDataSource.getAllArticles(pageNumber)
        return if (getArticlesResult.isDataHasGotSuccessfully()) {
            getArticlesResult.body()!!.posts!!.map { it.toArticleModel() }
        }else{
            throw Exception(getArticlesResult.getErrorMessageFromResponse())
        }
    }
}