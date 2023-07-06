package com.compose.codearticle.presentaion.screens.settingScreen.uiStates

data class SettingItemUiState(
    val icon : Int,
    var settingName : String,
    val subTitle:String = "",
    var defaultBox:  Unit ?
)
