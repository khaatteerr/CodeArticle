package com.compose.codearticle.presentaion.screens.settingScreen.uiStates

sealed class SettingUiEvent{
    object Logout:SettingUiEvent()
    data class DarkMode(val isDarkMode:Boolean):SettingUiEvent()
    data class ChangeLanguage(val language:Int):SettingUiEvent()
}
