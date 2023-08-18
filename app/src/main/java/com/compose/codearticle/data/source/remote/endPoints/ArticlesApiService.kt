package com.compose.codearticle.data.source.remote.endPoints

import com.compose.codearticle.data.source.remote.responseModels.ArticleResponseModel
import com.compose.codearticle.data.source.remote.responseModels.BaseApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApiService {
    @GET("posts")
    suspend fun getAllArticles(
        @Query("page") page: Int,
    ): Response<BaseApiResponse<List<ArticleResponseModel>>>
}