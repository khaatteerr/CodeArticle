package com.compose.codearticle.presentaion.utilities

import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState

fun List<PostUiState>.updateState(
    postId: String,
    update: (PostUiState) -> PostUiState
): List<PostUiState> =
    map { oldPost ->
        if (oldPost.id == postId) {
            update(oldPost)
        } else {
            oldPost
        }
    }


