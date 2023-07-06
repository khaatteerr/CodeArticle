package com.compose.codearticle.presentaion.screens.settingScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.codearticle.presentaion.screens.settingScreen.darkMode.DataStoreUtil
import com.compose.codearticle.presentaion.screens.settingScreen.darkMode.ThemeState
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.SettingUiEvent
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.SettingsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModel @Inject constructor(dataStoreUtil: DataStoreUtil) : ViewModel() {
    var settingsUiState by mutableStateOf(SettingsUiState())

    private val dataStore = dataStoreUtil.dataStore
    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.data.map { preferences ->
                ThemeState(preferences[DataStoreUtil.IS_DARK_MODE_KEY] ?: false)
            }.collect {
               settingsUiState = settingsUiState.copy(isDarkMode = it.isDarkMode)
            }
        }

    }

    fun toggleTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[DataStoreUtil.IS_DARK_MODE_KEY] = !(preferences[DataStoreUtil.IS_DARK_MODE_KEY] ?: false)
            }
        }
    }


    fun onEvent(event: SettingUiEvent) {
        when (event) {
            is SettingUiEvent.ChangeLanguage -> {}
            is SettingUiEvent.DarkMode -> {
                toggleTheme()
            }
           is SettingUiEvent.Logout -> {

            }
            is SettingUiEvent.OpenCloseBottomSheet->{
                settingsUiState = settingsUiState.copy(isDarkModeBottomSheetActive = settingsUiState.isDarkModeBottomSheetActive.not())
            }
        }
    }

}