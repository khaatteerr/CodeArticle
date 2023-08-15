package com.compose.codearticle.presentaion.screens.settingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.compose.codearticle.presentaion.navigation.Screen
import com.compose.codearticle.presentaion.screens.settingScreen.composabels.DarkModeBottomSheet
import com.compose.codearticle.presentaion.screens.settingScreen.composabels.SettingItemCard
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.Constants
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.SettingUiEvent
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.utilities.MainDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SettingScreen(
    navController: NavController,
    settingScreenViewModel: SettingScreenViewModel = hiltViewModel()
) {
    SettingContent(navController, settingScreenViewModel)
}

@Composable
fun SettingContent(navController: NavController, settingScreenViewModel: SettingScreenViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(start = 20.dp, end = 20.dp, top = 50.dp)
    ) {
        UserProfileSection(navController)
        AccountAndSecuritySection(settingScreenViewModel, navController)
    }
}

@Composable
fun UserProfileSection(navController: NavController) {
    Column(
        Modifier
            .fillMaxWidth()

    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "Back to home",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = 10.dp).size(20.dp)
            )

            Text(
                text = "Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Ubuntu,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Card(
            elevation = CardDefaults.cardElevation(1.dp),
            modifier = Modifier
                .padding(top = 30.dp)
                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(10.dp))
                .height(80.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = "https://images.unsplash.com/photo-1511367461989-f85a21fda167?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZmlsZXxlbnwwfHwwfHx8MA%3D%3D&w=1000&q=80"),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "Ahmed Khater",
                        fontSize = 15.sp,
                        fontFamily = Ubuntu,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Personal Info",
                        fontSize = 12.sp,
                        fontFamily = Ubuntu,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go specific Setting",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

        }
    }
}

@Composable
fun AccountAndSecuritySection(
    settingScreenViewModel: SettingScreenViewModel,
    navController: NavController
) {
    Text(
        text = "Account and Security",
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Ubuntu,
        color = MaterialTheme.colorScheme.inversePrimary.copy(0.5f),
        modifier = Modifier.padding(top = 25.dp, bottom = 10.dp, start = 5.dp)
    )

    Card(
       elevation = CardDefaults.cardElevation( 1.dp),
        modifier = Modifier
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)

        ) {
            settingScreenViewModel.settingsUiState.accountAndSecurity.forEach {
                SettingItemCard(it,settingScreenViewModel) {
                    when (it.settingName) {
                        Constants.DarkMode -> {
                            settingScreenViewModel.onEvent(SettingUiEvent.OpenCloseBottomSheet)
                        }
                        Constants.UpdateData -> {
                            navController.navigate(Screen.UpdateDataScreen.route)
                        }
                        Constants.ChangePassword -> {
                            navController.navigate(Screen.ChangePasswordScreen.route)
                        }

                    }

                }
            }
            if (settingScreenViewModel.settingsUiState.isDarkModeBottomSheetActive) {
                DarkModeBottomSheet(

                    onDismiss = {
                        settingScreenViewModel.onEvent(SettingUiEvent.OpenCloseBottomSheet)
                    })
            }

        }


    }
    Text(
        text = "General",
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Ubuntu,
        color = MaterialTheme.colorScheme.inversePrimary.copy(0.5f),
        modifier = Modifier.padding(top = 25.dp, bottom = 10.dp, start = 5.dp)
    )
    var logoutState by remember {
        mutableStateOf(false)
    }
    Card(
        elevation = CardDefaults.cardElevation( 1.dp),
        modifier = Modifier
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {

            settingScreenViewModel.settingsUiState.general.forEach {
                SettingItemCard(it,settingScreenViewModel) {
                    when (it.settingName) {
                        Constants.RateUs -> {
                            navController.navigate(Screen.RateUsScreen.route)
                        }

                        Constants.Privacy -> {
                            navController.navigate(Screen.PrivacyScreen.route)
                        }

                        Constants.Terms -> {
                            navController.navigate(Screen.TermsScreen.route)
                        }

                        Constants.Logout -> {
                            logoutState = !logoutState
                        }
                    }
                }

            }
        }
    }
    if (logoutState){
        MainDialog {
            val scope = rememberCoroutineScope()
            CircularProgressIndicator(modifier = Modifier
                .size(50.dp)
                  ,color = Color.Red)
            LaunchedEffect(key1 =  logoutState){
                scope .launch {
                    delay(3000)
                    navController.navigate(Screen.HomeScreen.route)
                }
            }
        }

    }
}