package com.compose.codearticle.presentaion.screens.registerScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false
) {
    val brush = Brush.linearGradient(
        listOf(
            Color(0xFF6E2FEA),
            Color(0xFFA369A2),
            Color(0xFFAE4B57),
        ), tileMode = TileMode.Clamp
    )

    Button(
        onClick = onClick,
        modifier = modifier.background(brush),
        shape = RoundedCornerShape(5.dp),
        enabled = isEnabled,

    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(20.dp))
        } else
            Text(text = "Continue", fontSize = 13.sp)
    }
}
