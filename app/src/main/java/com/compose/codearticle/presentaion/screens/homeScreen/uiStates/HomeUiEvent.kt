package com.compose.codearticle.presentaion.screens.homeScreen.uiStates

sealed class HomeUiEvent {
    data class SearchTextChanged(var text: String) : HomeUiEvent()
    object Search : HomeUiEvent()
    data class SearchBarActive(val active : Boolean):HomeUiEvent()
    data class AddToHistory(val searchText: String):HomeUiEvent()
    object ClearOrCloseSearchBar :HomeUiEvent()
    object ActiveDropDownMenu:HomeUiEvent()
 }
