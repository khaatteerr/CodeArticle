package com.compose.codearticle.presentaion.screens.authorization

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.compose.codearticle.presentaion.screens.loginScreen.LoginInScreen
import com.compose.codearticle.presentaion.screens.registerScreen.RegisterScreen

@Composable
fun AuthorizationScreen(navController: NavController) {

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
        var rotated by remember { mutableStateOf(false) }

        val rotation by animateFloatAsState(
            targetValue = if (rotated) 180f else 0f, animationSpec = tween(500), label = ""
        )

        Card(
            Modifier
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density

                },
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        ) {

            if (rotation <= 90f) {
                LoginInScreen(navController) {
                    rotated = !rotated
                }
            } else {
                RegisterScreen(navController) {
                    rotated = !rotated

                }

            }
        }
    }

}