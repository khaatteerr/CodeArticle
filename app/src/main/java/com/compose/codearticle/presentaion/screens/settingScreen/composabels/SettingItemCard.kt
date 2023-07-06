package com.compose.codearticle.presentaion.screens.settingScreen.composabels

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.codearticle.presentaion.screens.settingScreen.SettingScreenViewModel
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.Constants.DarkMode
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.SettingItemUiState
import com.compose.codearticle.presentaion.theme.Ubuntu

@SuppressLint("SuspiciousIndentation", "StateFlowValueCalledInComposition")
@Composable
fun SettingItemCard(
    settingItemUiState: SettingItemUiState,
    settingScreenViewModel: SettingScreenViewModel,
    onClick: () -> Unit
) {
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
            text = settingItemUiState.subTitle,
            fontSize = 12.sp,
            fontFamily = Ubuntu,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 5.dp),
            color = MaterialTheme.colorScheme.onBackground
        )



        if (settingItemUiState.settingName == DarkMode) {

            settingItemUiState.defaultBox = DefaultDarkModeBox()
        } else {

            if (settingItemUiState.settingName == "Logout") {
                settingItemUiState.defaultBox = null
            } else {

                settingItemUiState.defaultBox = DefaultSettingBox()
            }
        }

    }

}
