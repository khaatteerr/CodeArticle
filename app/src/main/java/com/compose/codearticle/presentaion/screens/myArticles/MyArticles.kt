package com.compose.codearticle.presentaion.screens.myArticles

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.codearticle.presentaion.screens.myArticles.composables.GridArticleCard

@Composable
fun MyArticles(navController: NavController , viewModel: MyArticlesViewModel = hiltViewModel()) {
    MyArticlesContent(navController , viewModel)
}

@Composable
fun MyArticlesContent(navController: NavController, viewModel: MyArticlesViewModel) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(10){
            GridArticleCard()
        }
    }

}
