package com.compose.codearticle.presentaion.screens.registerScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.codearticle.presentaion.theme.BottomBarIcon
import com.compose.codearticle.presentaion.theme.Orange
import com.compose.codearticle.presentaion.theme.Ubuntu

@Composable
fun OutlineInputField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Email,
    error: String? = null,
    isErrorVisible: Boolean = false,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    Column {
        TextField(
            value = text,
            onValueChange = onValueChange,
            modifier = modifier.border(
                0.3.dp,
                Color.White.copy(alpha = 0.5f),
                if (hint == "username") RoundedCornerShape(
                    topEnd = 10.dp,
                    topStart = 10.dp
                ) else RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            placeholder = {
                Text(hint, color = BottomBarIcon)
            },
            leadingIcon = {
                Icon(
                    icon,
                    contentDescription = hint,
                    tint = BottomBarIcon
                )
            },
            shape = if (hint == "username") RoundedCornerShape(
                topEnd = 10.dp,
                topStart = 10.dp
            ) else RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White.copy(alpha = 0.15f),
                unfocusedContainerColor = Color.White.copy(alpha = 0.15f),
                focusedBorderColor = Transparent,
                unfocusedBorderColor = Transparent
            ),
        )
        AnimatedVisibility(isErrorVisible) {
            Text(
                text = error ?: "",
                modifier = Modifier
                    .padding(start = 13.dp, top = 3.dp)
                    .fillMaxWidth(),
                fontFamily = Ubuntu,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                color = Red
            )
        }
    }
}
