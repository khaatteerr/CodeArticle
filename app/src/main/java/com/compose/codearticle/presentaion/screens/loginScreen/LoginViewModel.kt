package com.compose.codearticle.presentaion.screens.loginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.codearticle.domain.usecases.LoginUseCase
import com.compose.codearticle.domain.usecases.ValidateEmailUseCase
import com.compose.codearticle.domain.usecases.ValidatePasswordUseCase
import com.compose.codearticle.domain.utilities.InvalidInputTextException
import com.compose.codearticle.presentaion.screens.loginScreen.uiStates.LoginUiEvent
import com.compose.codearticle.presentaion.screens.loginScreen.uiStates.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    var loginUiState by mutableStateOf(LoginUiState(isLoading = false))
        private set

    private var _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()


    fun login() {
        viewModelScope.launch {
            loginUiState = loginUiState.copy(isLoading = true)
            val emailValidationResult =
                validateEmailUseCase(loginUiState.emailUiState.text)
            val passwordValidationResult =
                validatePasswordUseCase(loginUiState.passwordUiState.text)
            val hasValidationError = listOf(
                emailValidationResult,passwordValidationResult
            ).any{
                it.error != null
            }
            if (hasValidationError){
                loginUiState = loginUiState.copy(
                    emailUiState = loginUiState.emailUiState.copy(
                        errorMessage = emailValidationResult.error
                    ),
                    passwordUiState = loginUiState.passwordUiState.copy(
                        errorMessage = passwordValidationResult.error
                    ),
                )
                loginUiState = loginUiState.copy(isLoading = false)
            }   else {
                try {
                    var loginResult = loginUseCase(loginUiState.emailUiState.text, loginUiState.passwordUiState.text)

                    if (loginResult.token.isNotBlank())
                        _eventFlow.emit(UiEvent.LoginSuccess)
                    else
                        _eventFlow.emit(UiEvent.ShowMessage("Unknown Error"))
                    loginUiState = loginUiState.copy(isLoading = false)
                } catch (e: InvalidInputTextException) {
                    loginUiState = loginUiState.copy(
                        emailUiState = loginUiState.emailUiState.copy(
                            errorMessage = validateEmailUseCase(loginUiState.emailUiState.text).error
                        ),
                        passwordUiState = loginUiState.passwordUiState.copy(
                            errorMessage = validatePasswordUseCase(loginUiState.passwordUiState.text).error
                        ),
                    )
                    loginUiState = loginUiState.copy(isLoading = false)
                }catch (e: Exception) {
                    e.printStackTrace()
                    loginUiState = loginUiState.copy(isLoading = false)
                    _eventFlow.emit(UiEvent.ShowMessage(e.message.toString()))
                }
            }

        }
    }
    fun onEvent(action: LoginUiEvent) {
        loginUiState = when (action) {
            is LoginUiEvent.EmailChanged -> loginUiState.copy(
                emailUiState = loginUiState.emailUiState.copy(
                    errorMessage = null,
                    text = action.text
                )
            )

            is LoginUiEvent.PasswordChanged -> loginUiState.copy(
                passwordUiState = loginUiState.passwordUiState.copy(
                    errorMessage = null,
                    text = action.text
                )
            )
        }
    }

    sealed class UiEvent {
        object LoginSuccess : UiEvent()
        data class ShowMessage(var error: String) : UiEvent()
    }
}