package com.compose.codearticle.presentaion.screens.settingScreen.composabels

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.codearticle.presentaion.screens.settingScreen.darkMode.ThemeViewModel
import com.compose.codearticle.presentaion.theme.Ubuntu

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DefaultDarkModeBox(themeViewModel: ThemeViewModel = hiltViewModel()) {
    Row (verticalAlignment = Alignment.CenterVertically){

        val themeState by themeViewModel.themeState.collectAsState()

        Text(
            text = if (themeState.isDarkMode) "Dark" else "Light",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = Ubuntu
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Go to specific setting",
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }

}