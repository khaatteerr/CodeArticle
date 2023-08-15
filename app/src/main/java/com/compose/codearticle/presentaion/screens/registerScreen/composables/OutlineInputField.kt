package com.compose.codearticle.presentaion.screens.registerScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.codearticle.presentaion.theme.BottomBarIcon
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
 //   keyboardType: KeyboardType
) {
    Column {
        TextField(
            value = text,
            onValueChange = onValueChange,
       //     keyboardOptions = KeyboardOptions(keyboardType = keyboardType),

            modifier = modifier.border(
                0.3.dp,
                 White.copy(alpha = 0.5f),
                if (hint == "Email" || hint =="Password") RoundedCornerShape(
                    topEnd = 15.dp,
                    topStart = 15.dp
                ) else RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)
            ),
            singleLine = true,
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
            shape = if (hint == "Email"|| hint =="Password") RoundedCornerShape(
                topEnd = 15.dp,
                topStart = 15.dp
            ) else RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor =  White.copy(alpha = 0.15f),
                unfocusedContainerColor =  White.copy(alpha = 0.15f),
                focusedIndicatorColor = Transparent,
                disabledIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                cursorColor = White
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
