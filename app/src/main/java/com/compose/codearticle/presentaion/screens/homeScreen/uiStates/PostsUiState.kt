package com.compose.codearticle.presentaion.screens.homeScreen.uiStates

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class PostsUiState(
    var isLoading: Boolean = true,
    var posts: List<PostUiState> = emptyList(),
    var articles : MutableStateFlow<PagingData<PostUiState>> =  MutableStateFlow(value = PagingData.empty()) ,
    var stories : MutableStateFlow<PagingData<StoryUiState>> =  MutableStateFlow(value = PagingData.empty()) ,
    var textSearch: String = "",
    var searchResult: List<PostUiState> = emptyList(),
    var isSearchBarActive : Boolean = false,
    var searchHistory:  MutableList<String> = mutableListOf ("Kotlin", "Jetpack Compose")
)
