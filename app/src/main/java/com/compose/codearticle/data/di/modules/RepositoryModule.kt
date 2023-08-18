package com.compose.codearticle.data.di.modules

import com.compose.codearticle.data.repositories.ArticleRepositoryImpl
import com.compose.codearticle.data.repositories.UserRepositoryImpl
import com.compose.codearticle.domain.repositories.ArticlesRepository
import com.compose.codearticle.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Singleton
    @Provides
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideArticleRepository(articleRepositoryImpl: ArticleRepositoryImpl):ArticlesRepository{
        return articleRepositoryImpl
    }
}