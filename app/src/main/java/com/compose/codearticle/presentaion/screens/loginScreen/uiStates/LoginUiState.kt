package com.compose.codearticle.presentaion.screens.loginScreen.uiStates

import com.compose.codearticle.presentaion.screens.publishScreen.uiStates.InputFieldUiState

data class LoginUiState(
    val isLoading: Boolean = false,
    val emailUiState: InputFieldUiState = InputFieldUiState(),
    val passwordUiState: InputFieldUiState = InputFieldUiState()
)