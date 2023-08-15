package com.compose.codearticle.presentaion.screens.myArticles.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.compose.codearticle.presentaion.screens.myArticles.uiStates.MyArticleUiState

@Composable
fun GridArticleCard(myArticleUiState: MyArticleUiState) {
    Image(
        painter = rememberAsyncImagePainter(myArticleUiState.articleImageUrl),
        contentDescription = null,
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .height(myArticleUiState.height.dp)
            .background(
                Color.Gray
            ),
        contentScale = ContentScale.Crop
    )
}