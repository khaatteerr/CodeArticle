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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.compose.codearticle.R
import com.compose.codearticle.presentaion.screens.settingScreen.uiStates.SettingItemUiState
import com.compose.codearticle.presentaion.screens.settingScreen.composabels.SettingItemCard
import com.compose.codearticle.presentaion.theme.Ubuntu

@Composable
fun SettingScreen(navController: NavController) {
    SettingContent(navController)
}

@Composable
fun SettingContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        UserProfileSection(navController)
        AccountAndSecuritySection()
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
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back to home",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = 10.dp)
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
            elevation = CardDefaults.cardElevation(15.dp),
            modifier = Modifier

                .padding(top = 30.dp)
                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(10.dp))
                .height(80.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
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
                    modifier = Modifier.size(24.dp)
                )
            }

        }
    }
}

@Composable
fun AccountAndSecuritySection() {
    Text(
        text = "Account and Security",
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Ubuntu,
        color = MaterialTheme.colorScheme.inversePrimary.copy(0.5f),
        modifier = Modifier.padding(top = 25.dp, bottom = 10.dp, start = 5.dp)
    )
    val list = listOf(
        SettingItemUiState(R.drawable.update_data, "Update data"),
        SettingItemUiState(R.drawable.language, "Language", "English"),
        SettingItemUiState(R.drawable.password_check, "Change password"),
        SettingItemUiState(R.drawable.dark_mode, "Dark Mode", "night"),
        SettingItemUiState(R.drawable.rate_us, "Rate us"),
        SettingItemUiState(R.drawable.terms, "Terms & Conditions"),
        SettingItemUiState(R.drawable.privacy, "Privacy policy"),
        SettingItemUiState(R.drawable.logout, "Logout")
    )
    Card(
        elevation = CardDefaults.cardElevation(15.dp),
        modifier = Modifier
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(10.dp)
        ) {

            list.take(4).forEach {
                SettingItemCard(it)
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

    Card(
        elevation = CardDefaults.cardElevation(15.dp),
        modifier = Modifier

    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(10.dp)
        ) {
            list.takeLast(4).forEach {
                SettingItemCard(it)
            }
        }
    }
}