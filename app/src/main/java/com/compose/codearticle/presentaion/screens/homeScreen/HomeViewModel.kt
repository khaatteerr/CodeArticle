package com.compose.codearticle.presentaion.screens.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.HomeUiEvent
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    var postsUiState by mutableStateOf(PostsUiState(isLoading = true))

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

            is HomeUiEvent.ActiveDropDownMenu -> {

            }

        }
    }
}