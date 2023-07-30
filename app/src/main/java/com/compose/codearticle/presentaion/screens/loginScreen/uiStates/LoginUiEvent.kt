package com.compose.codearticle.presentaion.screens.loginScreen.uiStates

sealed class LoginUiEvent {
    data class EmailChanged(var text:String):LoginUiEvent()
    data class PasswordChanged(var text:String):LoginUiEvent()
}