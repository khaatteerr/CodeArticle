package com.compose.codearticle.presentaion.screens.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.codearticle.R
import com.compose.codearticle.presentaion.screens.splashScreen.uiState.SplashUiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                SplashUiEvent.HomeScreen -> navController.navigate(event.screen.route){
                    popUpTo(0)
                }
                SplashUiEvent.AuthorizationScreen -> navController.navigate(event.screen.route){
                    popUpTo(0)
                }

             }
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.logo_nobackground),
            contentDescription = null,
            contentScale = ContentScale.Crop, modifier = Modifier.size(200.dp)
        )
    }
}