package com.compose.codearticle.presentaion.screens.settingScreen.composabels

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.codearticle.presentaion.screens.settingScreen.darkMode.ThemeViewModel
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.utilities.bounceClick
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DarkModeBottomSheet(
    onDismiss: () -> Unit,
    themeViewModel: ThemeViewModel = hiltViewModel()
) {

    val themeState by themeViewModel.themeState.collectAsState()


    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = bottomSheetState,
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .bounceClick {
                            scope
                                .launch { bottomSheetState.hide() }
                                .invokeOnCompletion {
                                    onDismiss()
                                }
                        }
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.tertiary)
                        .weight(0.5f, false),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Close bottom sheet"
                    )
                }

                Text(
                    text = "App Settings",
                    fontWeight = Bold,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = Ubuntu, modifier = Modifier
                        .weight(1f), textAlign = TextAlign.Start
                )

            }
            Divider(
                Modifier
                    .padding(top = 10.dp, bottom = 30.dp)
                    .background(MaterialTheme.colorScheme.outline)
            )

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "APPEARANCE",
                    fontWeight = Bold,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                    fontFamily = Ubuntu
                )
                Divider(
                    Modifier
                        .padding(top = 10.dp)
                        .background(MaterialTheme.colorScheme.outline)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .bounceClick {
                                    scope
                                        .launch { bottomSheetState.hide() }
                                        .invokeOnCompletion {

                                            if (themeState.isDarkMode)
                                                themeViewModel.toggleTheme()
                                            onDismiss()
                                        }
                                }
                                .size(100.dp)
                                .padding(bottom = 10.dp)
                                .border(
                                    2.dp,
                                    if (themeState.isDarkMode) Color.Transparent else Color.Black,
                                    RoundedCornerShape(15.dp)
                                )
                                .clip(RoundedCornerShape(15.dp))
                                .background(Color.LightGray)

                        ) {

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .padding(top = 30.dp, start = 30.dp)
                                    .background(Color.White, RoundedCornerShape(topStart = 15.dp))
                            ) {
                                Text(
                                    text = "Aa", fontWeight = Bold,
                                    fontSize = 15.sp,
                                    color = Color.Black,
                                    fontFamily = Ubuntu,
                                    modifier = Modifier.padding(5.dp)
                                )
                            }

                        }
                        Text(
                            text = "Light", fontWeight = Bold,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = Ubuntu
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .bounceClick {
                                    scope
                                        .launch { bottomSheetState.hide() }
                                        .invokeOnCompletion {

                                            if (themeState.isDarkMode.not())
                                                themeViewModel.toggleTheme()
                                            onDismiss()
                                        }
                                }
                                .size(100.dp)
                                .padding(bottom = 10.dp)
                                .border(
                                    2.dp,
                                    if (themeState.isDarkMode) Color.Black else Color.Transparent,
                                    RoundedCornerShape(15.dp)
                                )
                                .clip(RoundedCornerShape(15.dp))
                                .background(Color.DarkGray)

                        ) {

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .padding(top = 30.dp, start = 30.dp)
                                    .background(Color.Black, RoundedCornerShape(topStart = 15.dp))
                            ) {
                                Text(
                                    text = "Aa", fontWeight = Bold,
                                    fontSize = 15.sp,
                                    color = Color.White,
                                    fontFamily = Ubuntu,
                                    modifier = Modifier.padding(5.dp)
                                )
                            }

                        }
                        Text(
                            text = "Dark", fontWeight = Bold,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = Ubuntu
                        )
                    }
                }
            }

        }
    }
}
