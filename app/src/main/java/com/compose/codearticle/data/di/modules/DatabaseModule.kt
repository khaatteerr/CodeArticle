package com.compose.codearticle.data.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.compose.codearticle.data.utilities.Constants.USER_SHARED_PREFERENCES_KEY
import com.compose.codearticle.presentaion.screens.settingScreen.darkMode.DataStoreUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil =
        DataStoreUtil(context)

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(USER_SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

//    @Singleton
//    @Provides
//    fun provideArticleDatabase(@ApplicationContext context: Context): ArticleDatabase {
//        return Room.databaseBuilder(
//            context,
//            ArticleDatabase::class.java,
//            "article.db"
//        )
//            .build()
//    }
//
//    @OptIn(ExperimentalPagingApi::class)
//    @Singleton
//    @Provides
//    fun provideArticlePager(
//        articleDatabase: ArticleDatabase,
//        articlesApiService: ArticlesApiService
//    ): Pager<Int, ArticleEntity> {
//        return Pager(
//            config = PagingConfig(pageSize = 20),
//            remoteMediator = ArticleRemoteMediator(
//                articleDatabase = articleDatabase,
//                articlesApiService = articlesApiService
//            ),
//            pagingSourceFactory = {
//                articleDatabase.dao.pagingSource()
//            }
//        )
//    }
}