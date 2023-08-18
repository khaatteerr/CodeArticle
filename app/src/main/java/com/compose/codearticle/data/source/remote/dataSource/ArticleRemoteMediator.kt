//package com.compose.codearticle.data.source.remote.dataSource
//
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import com.compose.codearticle.data.mapper.toArticleEntity
//import com.compose.codearticle.data.source.local.database.ArticleDatabase
//import com.compose.codearticle.data.source.local.database.ArticleEntity
//import com.compose.codearticle.data.source.remote.endPoints.ArticlesApiService
//import retrofit2.HttpException
//import java.io.IOException
//
//@OptIn(ExperimentalPagingApi::class)
//class ArticleRemoteMediator(
//    private val articleDatabase: ArticleDatabase,
//    private val articlesApiService: ArticlesApiService
//): RemoteMediator<Int, ArticleEntity>() {
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, ArticleEntity>
//    ): MediatorResult {
//        return try {
//            val loadKey = when(loadType) {
//                LoadType.REFRESH -> 1
//                LoadType.PREPEND -> return MediatorResult.Success(
//                    endOfPaginationReached = true
//                )
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                    if(lastItem == null) {
//                        1
//                    } else {
//                        (lastItem.id  / state.config.pageSize) + 1
//                    }
//                }
//            }
//
//            val articles = articlesApiService.getAllArticles(
//                page = loadKey,
//            )
//
//            articleDatabase.withTransaction {
//                if(loadType == LoadType.REFRESH) {
//                    articleDatabase.dao.clearAll()
//                }
//                val articleEntities = articles.data!!.map { it.toArticleEntity() }
//                articleDatabase.dao.upsertAll(articleEntities)
//            }
//
//            MediatorResult.Success(
//                endOfPaginationReached = articles.data!!.isEmpty()
//            )
//        } catch(e: IOException) {
//            MediatorResult.Error(e)
//        } catch(e: HttpException) {
//            MediatorResult.Error(e)
//        }
//
//    }
//}