package com.compose.codearticle.presentaion.screens.settingScreen.uiStates

sealed class SettingUiEvent{
    object Logout:SettingUiEvent()
     object OpenCloseBottomSheet:SettingUiEvent()
}
