package com.compose.codearticle.presentaion.screens.settingScreen.uiStates

import com.compose.codearticle.R
import org.intellij.lang.annotations.Language

object Constants {
    val accountAndSecurity = listOf(
        SettingItemUiState(R.drawable.update_data, "Update data", defaultBox = null),
    //    SettingItemUiState(R.drawable.language, "Language", "English"),
        SettingItemUiState(R.drawable.password_check, "Change password", defaultBox = null),
        SettingItemUiState(R.drawable.dark_mode, "Dark Mode" , defaultBox = null)
    )
    val generalSettings = listOf(
        SettingItemUiState(R.drawable.rate_us, "Rate us", defaultBox = null),
        SettingItemUiState(R.drawable.terms, "Terms & Conditions", defaultBox = null),
        SettingItemUiState(R.drawable.privacy, "Privacy policy", defaultBox = null),
        SettingItemUiState(R.drawable.logout, "Logout", defaultBox = null)
    )



    const val UpdateData = "Update data"
     const val ChangePassword = "Change password"
    const val DarkMode  = "Dark Mode"

    const val RateUs = "Rate us"
    const val Terms = "Terms & Conditions"
    const val Privacy = "Privacy policy"
    const val Logout  = "Logout"

}