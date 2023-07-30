package com.compose.codearticle.presentaion.screens.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.compose.codearticle.presentaion.screens.registerScreen.composables.OutlineInputField

@Composable
fun LoginInScreen(
    navController: NavController,
    loginScreenViewModel: LoginScreenViewModel = hiltViewModel()
){

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Url("https://assets4.lottiefiles.com/packages/lf20_4yabI8pgm7.json")
    )
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        LottieAnimation(
            composition = composition,
            contentScale = ContentScale.Crop,
            iterations = LottieConstants.IterateForever
        )

        Column(Modifier.padding(horizontal = 30.dp , vertical = 50.dp)) {
            OutlineInputField(
                text = "",
                onValueChange = {   },
                hint = "username",
                modifier = Modifier.fillMaxWidth(),
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Text,
            )
            OutlineInputField(
                text = "",
                onValueChange = {   },
                hint = "password",
                modifier = Modifier.fillMaxWidth(),
                icon = Icons.Default.ThumbUp,
                keyboardType = KeyboardType.Text,
            )
        }
    }
}