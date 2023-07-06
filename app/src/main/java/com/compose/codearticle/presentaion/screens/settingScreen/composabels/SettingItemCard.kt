package com.compose.codearticle.presentaion.screens.settingScreen.composabels

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.codearticle.presentaion.screens.settingScreen.SettingScreenViewModel
import com.compose.codearticle.presentaion.screens.settingScreen.darkMode.ThemeViewModel
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.Constants
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.Constants.DarkMode
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.SettingItemUiState
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.SettingUiEvent
import com.compose.codearticle.presentaion.theme.Ubuntu

@SuppressLint("SuspiciousIndentation")
@Composable
fun SettingItemCard(
    settingItemUiState: SettingItemUiState,
    settingScreenViewModel: SettingScreenViewModel,
    themeViewModel: ThemeViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    val themeState by themeViewModel.themeState.collectAsState()

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        Box(
            modifier = Modifier
                .size(30.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                painter = painterResource(id = settingItemUiState.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp),
                tint = if (settingItemUiState.settingName == "Logout") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
            )
        }

        Box(
            contentAlignment = Alignment.CenterStart, modifier = Modifier.weight(1f)
        ) {
            Text(
                text = settingItemUiState.settingName,
                fontSize = 15.sp,
                fontFamily = Ubuntu,
                fontWeight = FontWeight.Medium,
                color = if (settingItemUiState.settingName == "Logout") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground,
            )

        }
        Text(
            text = if (settingItemUiState.settingName == DarkMode && themeState.isDarkMode) "Dark" else settingItemUiState.subTitle,
            fontSize = 12.sp,
            fontFamily = Ubuntu,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 5.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        if (settingItemUiState.settingName != "Logout") {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Go to specific setting",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }


    }

}
