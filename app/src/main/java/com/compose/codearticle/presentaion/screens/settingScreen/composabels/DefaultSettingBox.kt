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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.codearticle.presentaion.screens.settingScreen.darkMode.ThemeViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DefaultSettingBox() {
    Row {
         Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Go to specific setting",
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }

}