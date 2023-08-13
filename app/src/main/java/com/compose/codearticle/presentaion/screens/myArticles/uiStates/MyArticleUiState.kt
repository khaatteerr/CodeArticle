package com.compose.codearticle.presentaion.screens.myArticles.uiStates

import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostedBy
import kotlin.random.Random

data class MyArticleUiState(
    val id: String = "-1",
    val postedBy: PostedBy? = null,
    val articleImageUrl: String = "",
    val onArticleClick: () -> Unit,
    val height: Int = Random.nextInt(100,300)
)
