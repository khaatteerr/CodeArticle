package com.compose.codearticle.presentaion.screens.settingScreen.uiStates

import com.compose.codearticle.R

object Constants {
    val accountAndSecurity = listOf(
        SettingItemUiState(R.drawable.update_data, "Update data"),
        SettingItemUiState(R.drawable.language, "Language", "English"),
        SettingItemUiState(R.drawable.password_check, "Change password"),
        SettingItemUiState(R.drawable.dark_mode, "Dark Mode", "night")
    )
    val generalSettings = listOf(
        SettingItemUiState(R.drawable.rate_us, "Rate us"),
        SettingItemUiState(R.drawable.terms, "Terms & Conditions"),
        SettingItemUiState(R.drawable.privacy, "Privacy policy"),
        SettingItemUiState(R.drawable.logout, "Logout")
    )
}