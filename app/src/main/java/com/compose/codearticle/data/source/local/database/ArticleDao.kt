//package com.compose.codearticle.data.source.local.database
//
//import androidx.paging.PagingSource
//import androidx.room.Dao
//import androidx.room.Query
//import androidx.room.Upsert
//
//
//@Dao
//interface ArticleDao {
//    @Upsert
//    suspend fun upsertAll(beers: List<ArticleEntity>)
//
//    @Query("SELECT * FROM ArticleEntity")
//    fun pagingSource(): PagingSource<Int, ArticleEntity>
//
//    @Query("DELETE FROM ArticleEntity")
//    suspend fun clearAll()
//}