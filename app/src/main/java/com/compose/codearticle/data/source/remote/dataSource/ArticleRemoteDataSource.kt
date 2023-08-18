package com.compose.codearticle.data.source.remote.dataSource

import com.compose.codearticle.data.source.remote.endPoints.ArticlesApiService
import com.compose.codearticle.data.source.remote.responseModels.ArticleResponseModel
import com.compose.codearticle.data.source.remote.responseModels.BaseApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ArticleRemoteDataSource @Inject constructor(private var articlesApiService: ArticlesApiService) {
    suspend fun getAllArticles(pageNumber:Int): Response<BaseApiResponse<List<ArticleResponseModel>>> =
        withContext(Dispatchers.IO) {
            articlesApiService.getAllArticles(pageNumber)
        }

}