package com.compose.codearticle.presentaion.screens.settingScreen.uiStates

data class SettingsUiState(
    val accountAndSecurity: List<SettingItemUiState> = Constants.accountAndSecurity,
    val general: List<SettingItemUiState> = Constants.generalSettings,
    var isDarkMode : Boolean = false ,
    var isDarkModeBottomSheetActive :Boolean= false
)
