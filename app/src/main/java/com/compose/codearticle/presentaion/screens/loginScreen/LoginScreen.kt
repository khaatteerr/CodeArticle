package com.compose.codearticle.presentaion.screens.loginScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.compose.codearticle.presentaion.navigation.Screen
import com.compose.codearticle.presentaion.screens.loginScreen.uiStates.LoginUiEvent
import com.compose.codearticle.presentaion.screens.registerScreen.composables.LoadingButton
import com.compose.codearticle.presentaion.screens.registerScreen.composables.OutlineInputField
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.exyte.animatednavbar.utils.noRippleClickable
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginInScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Url("https://assets4.lottiefiles.com/packages/lf20_4yabI8pgm7.json")
    )
     val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                LoginViewModel.UiEvent.LoginSuccess -> navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(
                        0
                    )
                }

                is LoginViewModel.UiEvent.ShowMessage -> snackBarHostState.showSnackbar(
                    event.error
                )


            }
        }
    }
    Scaffold(
        modifier = Modifier.background(Black),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Black)
        ) {

            LottieAnimation(
                composition = composition,
                contentScale = ContentScale.Crop,
                iterations = LottieConstants.IterateForever
            )

            Column(
                Modifier.padding(start = 30.dp, end = 30.dp, top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Hello Again!",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    letterSpacing = 2.sp,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "Welcome back you've been missed!",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Thin,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp),
                    textAlign = TextAlign.Start

                )
                OutlineInputField(
                    text = viewModel.loginUiState.emailUiState.text,
                    hint = "Email",
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { viewModel.onEvent(LoginUiEvent.EmailChanged(it)) },
                     isErrorVisible = viewModel.loginUiState.emailUiState.errorMessage != null,
                    error = viewModel.loginUiState.emailUiState.errorMessage
                )

                OutlineInputField(
                    text = viewModel.loginUiState.passwordUiState.text,
                    hint = "Password",
                    modifier = Modifier.fillMaxWidth(),
                    icon = Icons.Default.Password,
                    onValueChange = { viewModel.onEvent(LoginUiEvent.PasswordChanged(it)) },
                     isErrorVisible = viewModel.loginUiState.passwordUiState.errorMessage != null,
                    error = viewModel.loginUiState.passwordUiState.errorMessage
                )
                LoadingButton(
                    onClick = viewModel::login,
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    isEnabled = !viewModel.loginUiState.isLoading,
                    isLoading = viewModel.loginUiState.isLoading
                )
                Text(
                    text = "don't have an account? sign up",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .noRippleClickable {
                            navController.navigate(Screen.RegisterScreen.route)
                        },
                    fontFamily = Ubuntu,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }
        }
    }

}