package com.compose.codearticle.presentaion.screens.homeScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.compose.codearticle.domain.usecases.GetAllArticlesUseCase
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.HomeUiEvent
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.ImageMedia
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostedBy
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostsUiState
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.StoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllArticlesUseCase: GetAllArticlesUseCase,

    ) : ViewModel() {


    var postsUiState by mutableStateOf(PostsUiState(isLoading = true))

    private var _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()
    private fun search() {
        if (postsUiState.textSearch.trim().isBlank())
            postsUiState = postsUiState.copy(searchResult = emptyList())
        else {
            try {
                postsUiState = postsUiState.copy(isLoading = true)
                postsUiState = postsUiState.copy(textSearch = "")
                postsUiState = postsUiState.copy(isSearchBarActive = false)
                // GET SEARCH RESULT FORM USE CASE
                postsUiState = postsUiState.copy(searchResult = emptyList()/*searchResult*/)

            } catch (e: Exception) {
                // HANDLE ERROR
                postsUiState = postsUiState.copy(isLoading = false)
            }
        }


    }

    init {

        viewModelScope.launch {
            try {
                getAllArticlesUseCase.invoke()
                    .cachedIn(viewModelScope).collect {
                        val newArticlesList = it.map { article ->
                            PostUiState(
                                postDescription = article.content,
                                isLiked = article.liked,
                                createdAt = article.title,
                                id = article.id.toString(),
                                isFollowing = false,
                                postedBy = PostedBy(
                                    "1",
                                    article.attachment.image,
                                    article.user.userName
                                ),
                                commentsCount = article.commentsCount,
                                likes = 10,
                                isDropDownMenuActive = false,
                                isSavedToLocal = false,
                                media = ImageMedia(
                                    article.attachment.image,
                                    article.attachment.imageHeight.toInt(),
                                    article.attachment.imageWidth.toInt()
                                )
                            )
                        }
                        val stories = it.map { article ->
                            StoryUiState(
                                postedBy = PostedBy(
                                    "-1", article.attachment.image,
                                    article.user.userName
                                ), storyUrl = article.attachment.image
                            )
                        }
                        postsUiState.articles.value = newArticlesList
                        postsUiState.stories.value = stories
                    }
            } catch (e: Exception) {
                e.printStackTrace()

                _eventFlow.emit(UiEvent.ShowMessage(e.message.toString()))

            }
        }

    }


    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.SearchTextChanged -> {
                postsUiState = postsUiState.copy(textSearch = event.text)
            }

            is HomeUiEvent.Search -> {
                search()
            }

            is HomeUiEvent.SearchBarActive -> {
                postsUiState = postsUiState.copy(isSearchBarActive = true)
            }

            is HomeUiEvent.AddToHistory -> {
                postsUiState.searchHistory.add(event.searchText)
            }

            is HomeUiEvent.ClearOrCloseSearchBar -> {
                if (postsUiState.textSearch.isBlank()) {
                    postsUiState = postsUiState.copy(isSearchBarActive = false)
                }
                postsUiState = postsUiState.copy(textSearch = "")

            }

        }
    }

    sealed class UiEvent {
        data class ShowMessage(var message: String) : UiEvent()
    }
}
