package com.compose.codearticle.presentaion.screens.registerScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.codearticle.presentaion.navigation.Screen
import com.compose.codearticle.presentaion.screens.loginScreen.uiStates.LoginUiEvent
import com.compose.codearticle.presentaion.screens.registerScreen.composables.LoadingButton
import com.compose.codearticle.presentaion.screens.registerScreen.composables.OutlineInputField
import com.compose.codearticle.presentaion.screens.registerScreen.uiStates.RegisterUiEvent
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.exyte.animatednavbar.utils.noRippleClickable
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel(),
    goToLogin: () -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {

                RegisterViewModel.UiEvent.RegisterSuccess -> navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(
                        0
                    )
                }

                is RegisterViewModel.UiEvent.ShowMessage -> snackBarHostState.showSnackbar(
                    event.error
                )

             }
        }
    }
    Scaffold(
        modifier = Modifier .graphicsLayer {
            rotationY = 180f

        },
        containerColor = Color.Transparent,
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        Box(modifier = Modifier
           ) {

            Column(
                Modifier

                    .padding(start = 30.dp, end = 30.dp, top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Welcome with us!",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    letterSpacing = 2.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "Create new account",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Thin,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    textAlign = TextAlign.Start

                )


                OutlineInputField(
                    text = viewModel.registerUiState.emailUiState.text,
                    hint = "Email",
                    modifier = Modifier.fillMaxWidth(),
                    icon = Icons.Default.Email,
                    onValueChange = { viewModel.onEvent(RegisterUiEvent.EmailChanged(it)) },
                    isErrorVisible = viewModel.registerUiState.emailUiState.errorMessage != null,
                    error = viewModel.registerUiState.emailUiState.errorMessage
                )
                OutlineInputField(
                    text = viewModel.registerUiState.userNameUiState.text,
                    hint = "Username",
                    icon = Icons.Default.Person4,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { viewModel.onEvent(RegisterUiEvent.UserNameChanged(it)) },
                    isErrorVisible = viewModel.registerUiState.userNameUiState.errorMessage != null,
                    error = viewModel.registerUiState.userNameUiState.errorMessage
                )
                Spacer(modifier = Modifier.height(50.dp))

                OutlineInputField(
                    text = viewModel.registerUiState.passwordUiState.text,
                    hint = "Password",
                    icon = Icons.Default.Password,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { viewModel.onEvent(RegisterUiEvent.PasswordChanged(it)) },
                    isErrorVisible = viewModel.registerUiState.passwordUiState.errorMessage != null,
                    error = viewModel.registerUiState.passwordUiState.errorMessage
                )

                OutlineInputField(
                    text = viewModel.registerUiState.confirmPasswordUiState.text,
                    hint = "Confirm password",
                    modifier = Modifier.fillMaxWidth(),
                    icon = Icons.Default.VerifiedUser,
                    onValueChange = { viewModel.onEvent(RegisterUiEvent.ConfirmPasswordChanged(it)) },
                    isErrorVisible = viewModel.registerUiState.confirmPasswordUiState.errorMessage != null,
                    error = viewModel.registerUiState.confirmPasswordUiState.errorMessage
                )

                LoadingButton(
                    onClick = viewModel::register,
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    isEnabled = !viewModel.registerUiState.isLoading,
                    isLoading = viewModel.registerUiState.isLoading
                )
                Text(
                    text = "have an account already? login now",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .noRippleClickable {
                            goToLogin()
                        },
                    fontFamily = Ubuntu,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}