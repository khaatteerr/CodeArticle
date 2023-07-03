package com.compose.codearticle.presentaion.screens.settingScreen.uiStates

sealed class SettingUiEvent{
    object Logout:SettingUiEvent()
    object  DarkMode:SettingUiEvent()
    data class ChangeLanguage(val language:Int):SettingUiEvent()
}
