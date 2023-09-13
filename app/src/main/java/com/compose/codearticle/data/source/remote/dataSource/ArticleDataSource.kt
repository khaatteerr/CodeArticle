package com.compose.codearticle.data.source.remote.dataSource

import android.net.http.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.compose.codearticle.data.source.remote.responseModels.ArticleResponseModel
import com.compose.codearticle.domain.models.ArticleModel
import okio.IOException
import javax.inject.Inject

class ArticleDataSource @Inject constructor(
    private val articleRemoteDataSource: ArticleRemoteDataSource
) : PagingSource<Int, ArticleModel>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        return try {

            val currentPage = params.key ?: 1
            val articles = articleRemoteDataSource.getAllArticles(
                pageNumber = currentPage
            )
            LoadResult.Page(
                data = articles.body()!!.posts!!.map { it.toArticleModel() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (articles.body()!!.posts!!.isEmpty()) null else currentPage + 1
            )

        }catch (exception:IOException){
            return LoadResult.Error(exception)
        }catch (exception:retrofit2.HttpException){
            return LoadResult.Error(exception)

        }
     }
    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? {
        return state.anchorPosition
     }



}