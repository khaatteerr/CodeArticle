package com.compose.codearticle.presentaion.screens.registerScreen.uiStates

sealed class RegisterUiEvent {
    data class UserNameChanged(var text: String) : RegisterUiEvent()
    data class EmailChanged(var text: String):RegisterUiEvent()
    data class PasswordChanged(var text: String):RegisterUiEvent()
    data class ConfirmPasswordChanged(var text: String):RegisterUiEvent()
}
