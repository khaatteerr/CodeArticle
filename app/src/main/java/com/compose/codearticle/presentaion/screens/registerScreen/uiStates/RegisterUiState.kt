package com.compose.codearticle.presentaion.screens.registerScreen.uiStates

import com.compose.codearticle.presentaion.screens.publishScreen.uiStates.InputFieldUiState

data class RegisterUiState(
    val isLoading: Boolean = false,
    val userNameUiState: InputFieldUiState = InputFieldUiState(),
    val emailUiState: InputFieldUiState = InputFieldUiState(),
    val passwordUiState: InputFieldUiState = InputFieldUiState(),
    val confirmPasswordUiState: InputFieldUiState = InputFieldUiState()
)
