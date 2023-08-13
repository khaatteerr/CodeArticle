package com.compose.codearticle.presentaion.screens.myArticles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostedBy
import com.compose.codearticle.presentaion.screens.myArticles.uiStates.MyArticleUiState
import com.compose.codearticle.presentaion.screens.myArticles.uiStates.MyArticlesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyArticlesViewModel @Inject constructor() : ViewModel() {

    var articlesUiState by mutableStateOf(MyArticlesUiState(isLoading = true))

    init {
        val list = listOf(
            MyArticleUiState(
                "8",
                PostedBy("1", "", "Ahmed khater"),
                "https://nationaltoday.com/wp-content/uploads/2022/04/Cristiano-Ronaldo-Birthday.jpg",
                {}),  MyArticleUiState(
                "1",
                PostedBy("1", "", "Ahmed khater"),
                "https://i.redd.it/vr2o7iiob5k91.jpg",
                {}),  MyArticleUiState(
                "2",
                PostedBy("1", "", "Ahmed khater"),
                "https://cloudfront-us-east-2.images.arcpublishing.com/reuters/XEEPZPVPD5O4RO76334OJYCQ4M.jpg",
                {}),  MyArticleUiState(
                "3",
                PostedBy("1", "", "Ahmed khater"),
                "https://i.redd.it/vr2o7iiob5k91.jpg",
                {}),  MyArticleUiState(
                "4",
                PostedBy("1", "", "Ahmed khater"),
                "https://cdn.24.co.za/files/Cms/General/d/2694/af1c6139c4a54413844c145b2e67dee4.jpg",
                {}),  MyArticleUiState(
                "5",
                PostedBy("1", "", "Ahmed khater"),
                "https://i.redd.it/vr2o7iiob5k91.jpg",
                {}),  MyArticleUiState(
                "6",
                PostedBy("1", "", "Ahmed khater"),
                "https://i.redd.it/vr2o7iiob5k91.jpg",
                {}),  MyArticleUiState(
                "7",
                PostedBy("1", "", "Ahmed khater"),
                "https://cdn.24.co.za/files/Cms/General/d/2694/af1c6139c4a54413844c145b2e67dee4.jpg",
                {}),
        )

        articlesUiState = articlesUiState.copy(myArticles = list)
    }
}