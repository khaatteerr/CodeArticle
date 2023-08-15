package com.compose.codearticle.presentaion.screens.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.codearticle.domain.usecases.IsUserSplashUseCase
import com.compose.codearticle.presentaion.screens.splashScreen.uiState.SplashUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(isUserSplashUseCase: IsUserSplashUseCase) :ViewModel() {
    private var _eventFlow = MutableSharedFlow<SplashUiEvent>()
    val eventFlow: SharedFlow<SplashUiEvent> = _eventFlow.asSharedFlow()
    init {
        viewModelScope.launch {
            val isSplash = isUserSplashUseCase()
            _eventFlow.emit(if(isSplash) SplashUiEvent.AuthorizationScreen else SplashUiEvent.HomeScreen)
        }
    }
}