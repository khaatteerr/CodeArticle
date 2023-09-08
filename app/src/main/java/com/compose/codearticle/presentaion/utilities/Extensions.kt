package com.compose.codearticle.presentaion.utilities

import androidx.paging.PagingData
import androidx.paging.map
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

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

fun Flow<PagingData<PostUiState>>.updateState(
    postId: String,
    update: (PostUiState) -> PostUiState
): Flow<PagingData<PostUiState>> =
      map { pagingData ->
        pagingData.map { oldPost ->
            if (oldPost.id == postId) {
                update(oldPost)
            } else {
                oldPost
            }
        }
    }







