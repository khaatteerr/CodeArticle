package com.compose.codearticle.data.di.modules

import android.content.Context
import com.compose.codearticle.presentaion.screens.settingScreen.darkMode.DataStoreUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDataStoreUtil(@ApplicationContext context: Context):DataStoreUtil = DataStoreUtil(context)

}