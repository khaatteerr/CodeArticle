package com.compose.codearticle.presentaion.screens.settingScreen.uiStates

data class SettingsUiState(
    val accountAndSecurity: List<SettingItemUiState> = Constants.accountAndSecurity,
    val general: List<SettingItemUiState> = Constants.generalSettings,
    var isDarkModeBottomSheetActive :Boolean= false
)
