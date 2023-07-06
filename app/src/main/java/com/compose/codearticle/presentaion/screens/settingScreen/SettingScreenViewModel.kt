package com.compose.codearticle.presentaion.screens.settingScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.codearticle.presentaion.screens.settingScreen.darkMode.DataStoreUtil
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.SettingUiEvent
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.SettingsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModel @Inject constructor( ) : ViewModel() {
    var settingsUiState by mutableStateOf(SettingsUiState())

    fun onEvent(event: SettingUiEvent) {
        when (event) {

            is SettingUiEvent.Logout -> {}
            is SettingUiEvent.OpenCloseBottomSheet->{
                settingsUiState = settingsUiState.copy(isDarkModeBottomSheetActive = settingsUiState.isDarkModeBottomSheetActive.not())
            }
        }
    }

}