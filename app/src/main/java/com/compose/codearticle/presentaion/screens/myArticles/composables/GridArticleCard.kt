package com.compose.codearticle.presentaion.screens.myArticles.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.Text
import com.compose.codearticle.R

@Composable
fun GridArticleCard() {
Image(painter = painterResource(id = R.drawable.heart_nav), contentDescription =null ,Modifier.size(80.dp).background(
    Color.Red))}