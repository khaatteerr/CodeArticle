package com.compose.codearticle.presentaion.screens.myArticles

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.codearticle.presentaion.screens.myArticles.composables.GridArticleCard

@Composable
fun MyArticles(navController: NavController, viewModel: MyArticlesViewModel = hiltViewModel()) {
    MyArticlesContent(navController, viewModel)
}

 @Composable
fun MyArticlesContent(navController: NavController, viewModel: MyArticlesViewModel) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues( 8.dp), verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(viewModel.articlesUiState.myArticles, key = { it.id }) {
            GridArticleCard(it)
        }
    }

}
