package com.compose.codearticle.presentaion.screens.settingScreen.uiStates

import com.compose.codearticle.R
import org.intellij.lang.annotations.Language

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



    const val UpdateData = "Update data"
    const val Language = "Language"
    const val ChangePassword = "Change password"
    const val DarkMode  = "Dark Mode"

    const val RateUs = "Rate us"
    const val Terms = "Terms & Conditions"
    const val Privacy = "Privacy policy"
    const val Logout  = "Logout"

}