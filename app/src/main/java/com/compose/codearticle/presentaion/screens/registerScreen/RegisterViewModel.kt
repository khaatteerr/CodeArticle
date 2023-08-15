package com.compose.codearticle.presentaion.screens.registerScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.codearticle.domain.usecases.RegisterUseCase
import com.compose.codearticle.domain.usecases.ValidateConfirmPasswordUseCase
import com.compose.codearticle.domain.usecases.ValidateEmailUseCase
import com.compose.codearticle.domain.usecases.ValidatePasswordUseCase
import com.compose.codearticle.domain.usecases.ValidateUserNameUseCase
import com.compose.codearticle.domain.utilities.InvalidInputTextException
import com.compose.codearticle.presentaion.screens.registerScreen.uiStates.RegisterUiEvent
import com.compose.codearticle.presentaion.screens.registerScreen.uiStates.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    var validateEmailUseCase: ValidateEmailUseCase,
    var validatePasswordUseCase: ValidatePasswordUseCase,
    var validateUserNameUseCase: ValidateUserNameUseCase,
    var validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase,
    var registerUseCase: RegisterUseCase
) : ViewModel() {

    var registerUiState by mutableStateOf(RegisterUiState(isLoading = false))
        private set

    private var _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    fun register() {
        viewModelScope.launch {
            registerUiState = registerUiState.copy(isLoading = true)
            val usernameValidation =
                validateUserNameUseCase(registerUiState.userNameUiState.text)
            val emailValidation =
                validateEmailUseCase(registerUiState.emailUiState.text)
            val passwordValidation =
                validatePasswordUseCase(registerUiState.passwordUiState.text)
            val passwordConfirmationValidation =
                validateConfirmPasswordUseCase(
                    registerUiState.passwordUiState.text,
                    registerUiState.confirmPasswordUiState.text
                )

            val hasValidationError = listOf(
                usernameValidation,
                emailValidation,
                passwordValidation,
                passwordConfirmationValidation
            ).any { it.error != null }

            if (hasValidationError) {
                registerUiState = registerUiState.copy(
                    userNameUiState = registerUiState.userNameUiState.copy(
                        errorMessage = usernameValidation.error
                    ),
                    emailUiState = registerUiState.emailUiState.copy(
                        errorMessage = emailValidation.error
                    ),
                    passwordUiState = registerUiState.passwordUiState.copy(
                        errorMessage = passwordValidation.error
                    ),
                    confirmPasswordUiState = registerUiState.confirmPasswordUiState.copy(
                        errorMessage = passwordConfirmationValidation.error
                    )
                )
                registerUiState = registerUiState.copy(isLoading = false)


            } else {
                try {
                    val registerResult = registerUseCase(
                        registerUiState.userNameUiState.text,
                        registerUiState.emailUiState.text,
                        registerUiState.passwordUiState.text,
                        registerUiState.confirmPasswordUiState.text
                    )

                    if (registerResult.token.isNotBlank())
                        _eventFlow.emit(UiEvent.RegisterSuccess)
                    else
                        _eventFlow.emit(UiEvent.ShowMessage("Unknown Error"))
                    registerUiState = registerUiState.copy(isLoading = false)


                } catch (e: InvalidInputTextException) {
                    registerUiState = registerUiState.copy(
                        userNameUiState = registerUiState.userNameUiState.copy(
                            errorMessage = validateUserNameUseCase(registerUiState.userNameUiState.text).error
                        ),
                        emailUiState = registerUiState.emailUiState.copy(
                            errorMessage = validateEmailUseCase(registerUiState.emailUiState.text).error
                        ),
                        passwordUiState = registerUiState.passwordUiState.copy(
                            errorMessage = validatePasswordUseCase(registerUiState.passwordUiState.text).error
                        ),
                        confirmPasswordUiState = registerUiState.confirmPasswordUiState.copy(
                            errorMessage = validateConfirmPasswordUseCase(
                                registerUiState.passwordUiState.text,
                                registerUiState.confirmPasswordUiState.text
                            ).error
                        )
                    )
                    registerUiState = registerUiState.copy(isLoading = false)

                } catch (e: Exception) {
                    e.printStackTrace()
                    registerUiState = registerUiState.copy(isLoading = false)
                    _eventFlow.emit(UiEvent.ShowMessage(e.message.toString()))
                }
            }


        }
    }

    fun onEvent(action: RegisterUiEvent) {
        registerUiState = when (action) {
            is RegisterUiEvent.ConfirmPasswordChanged -> registerUiState.copy(
                confirmPasswordUiState = registerUiState.confirmPasswordUiState.copy(
                    text = action.text,
                    errorMessage = null
                )
            )

            is RegisterUiEvent.EmailChanged -> registerUiState.copy(
                emailUiState = registerUiState.emailUiState.copy(
                    text = action.text,
                    errorMessage = null
                )
            )

            is RegisterUiEvent.PasswordChanged -> registerUiState.copy(
                passwordUiState = registerUiState.passwordUiState.copy(
                    text = action.text,
                    errorMessage = null
                )
            )


            is RegisterUiEvent.UserNameChanged -> registerUiState.copy(
                userNameUiState = registerUiState.userNameUiState.copy(
                    text = action.text,
                    errorMessage = null
                )
            )
        }
    }

    sealed class UiEvent {
        object RegisterSuccess : UiEvent()
        data class ShowMessage(var error: String) : UiEvent()
    }
}