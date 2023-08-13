package com.compose.codearticle.presentaion.screens.myArticles.uiStates

data class MyArticlesUiState(
    val myArticles: List<MyArticleUiState> = emptyList(),
    var isLoading: Boolean = true,
)
