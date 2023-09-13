package com.compose.codearticle.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.compose.codearticle.data.source.remote.dataSource.ArticleDataSource
import com.compose.codearticle.data.source.remote.dataSource.ArticleRemoteDataSource
import com.compose.codearticle.data.utilities.getErrorMessageFromResponse
import com.compose.codearticle.data.utilities.isDataHasGotSuccessfully
import com.compose.codearticle.domain.models.ArticleModel
import com.compose.codearticle.domain.repositories.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleRemoteDataSource: ArticleRemoteDataSource
) : ArticlesRepository {
    override suspend fun getAllArticles(): Flow<PagingData<ArticleModel>> {
        return Pager(
                config = PagingConfig(pageSize = 10, prefetchDistance = 2),
                pagingSourceFactory = {
                    ArticleDataSource(articleRemoteDataSource)
                }
            ).flow

    }
}