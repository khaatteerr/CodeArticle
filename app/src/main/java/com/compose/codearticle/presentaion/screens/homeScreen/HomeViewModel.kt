package com.compose.codearticle.presentaion.screens.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.codearticle.domain.usecases.GetAllArticlesUseCase
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.HomeUiEvent
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.ImageMedia
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostedBy
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostsUiState
import com.compose.codearticle.presentaion.utilities.updateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
     private val getAllArticlesUseCase: GetAllArticlesUseCase

) : ViewModel() {

//    val articlePagingFlow = pager
//        .flow
//        .map { pagingData ->
//            pagingData.map { it.toArticleModel() }
//        }
//        .cachedIn(viewModelScope)

    var postsUiState by mutableStateOf(PostsUiState(isLoading = true))
    private var _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()
    private fun search() {
        if (postsUiState.textSearch.trim().isBlank())
            postsUiState = postsUiState.copy(searchResult = emptyList())
        else {
            try {
                postsUiState = postsUiState.copy(isLoading = true)
                postsUiState = postsUiState.copy(textSearch = "")
                postsUiState = postsUiState.copy(isSearchBarActive = false)
                // GET SEARCH RESULT FORM USE CASE
                postsUiState = postsUiState.copy(searchResult = emptyList()/*searchResult*/)

            } catch (e: Exception) {
                // HANDLE ERROR
                postsUiState = postsUiState.copy(isLoading = false)
            }
        }


    }

    init {

        viewModelScope.launch {
            try {
                getAllArticlesUseCase.invoke(1).collect {
                    val newArticlesList = it.map { article ->
                        PostUiState(
                            postDescription = article.content,
                            isLiked = article.liked,
                            createdAt = article.title,
                            id = article.id.toString(),
                            changeLikeState = {
                                postsUiState = postsUiState.copy(
                                    posts = postsUiState.posts.updateState(article.id.toString()) { oldPost ->
                                        oldPost.copy(
                                            isLiked = oldPost.isLiked.not(),
                                            likes = if (oldPost.isLiked) oldPost.likes?.dec() else oldPost.likes?.inc()
                                        )
                                    }
                                )

                            },
                            changeDropDownMenuState = {
                                postsUiState = postsUiState.copy(
                                    posts = postsUiState.posts.updateState(article.id.toString()) { oldPost ->
                                        oldPost.copy(isDropDownMenuActive = oldPost.isDropDownMenuActive.not())
                                    }
                                )
                            },
                            SaveToLocal = {
                                postsUiState = postsUiState.copy(
                                    posts = postsUiState.posts.updateState(article.id.toString()) { oldPost ->
                                        oldPost.copy(isSavedToLocal = oldPost.isSavedToLocal.not())
                                    }
                                )
                            },
                            isFollowing = false,
                            postedBy = PostedBy("1",  article.attachment.image , article.user.userName),
                            comments_count = article.commentsCount,
                            likes = 10,
                            isDropDownMenuActive = false,
                            isSavedToLocal = false,
                            media = ImageMedia(article.attachment.image,article.attachment.imageHeight.toInt(),article.attachment.imageWidth.toInt())
                        )
                    }
                    postsUiState = postsUiState.copy(posts = newArticlesList, isLoading = false)
                }
            } catch (e: Exception) {
                _eventFlow.emit(UiEvent.ShowMessage(e.message.toString()))
                postsUiState = postsUiState.copy(
                    isLoading = false
                )
            }
        }
//        val oldList = listOf(
//            PostModel(
//                PostedBy(
//                    "1",
//                    avatar = "https://nationaltoday.com/wp-content/uploads/2022/04/Cristiano-Ronaldo-Birthday.jpg",
//                    "Ahmed Khater"
//                ),
//                "2 hours ago",
//
//                postDescription = "Sky The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
//                id = "1",
//                likes = 3,
//                comments_count = 10,
//                media = ImageMedia("https://i.redd.it/vr2o7iiob5k91.jpg", 1080, 720),
//            ),
//
//            PostModel(
//                PostedBy(
//                    "2",
//                    avatar = "https://cloudfront-us-east-2.images.arcpublishing.com/reuters/XEEPZPVPD5O4RO76334OJYCQ4M.jpg",
//                    "Khaled Abady"
//                ),
//
//                "5 hours ago",
//                media = ImageMedia("https://i.redd.it/vr2o7iiob5k91.jpg", 1080, 720),
//                "Sky: The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
//                id = "2",
//                likes = 4,
//                comments_count = 20
//            ),
//            PostModel(
//                PostedBy(
//                    "3",
//                    avatar = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Sergio_Ramos_Interview_2021.jpg/280px-Sergio_Ramos_Interview_2021.jpg",
//                    "Salem Gamal"
//                ),
//
//                "yesterday",
//                media = ImageMedia("https://i.redd.it/vr2o7iiob5k91.jpg", 1080, 720),
//                "Sky:The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
//                id = "3",
//                likes = 10,
//                comments_count = 15
//            ),
//            PostModel(
//                PostedBy(
//                    "4",
//                    avatar = "https://cdn.24.co.za/files/Cms/General/d/2694/af1c6139c4a54413844c145b2e67dee4.jpg",
//                    "Mohamed Reda"
//                ),
//
//                "2 hours ago",
//                media = ImageMedia("https://i.redd.it/vr2o7iiob5k91.jpg", 1080, 720),
//                "Sky:The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
//                id = "4",
//                likes = 20,
//                comments_count = 17
//            ),
//            PostModel(
//                PostedBy(
//                    "1",
//                    avatar = "https://nationaltoday.com/wp-content/uploads/2022/04/Cristiano-Ronaldo-Birthday.jpg",
//                    "Ahmed Khater"
//                ),
//                "2 hours ago",
//                media = ImageMedia("https://i.redd.it/vr2o7iiob5k91.jpg", 1080, 720),
//                "Sky:The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
//                id = "5",
//                likes = 5,
//                comments_count = 2
//            ),
//            PostModel(
//                PostedBy(
//                    "2",
//                    avatar = "https://cloudfront-us-east-2.images.arcpublishing.com/reuters/XEEPZPVPD5O4RO76334OJYCQ4M.jpg",
//                    "Khaled Abady"
//                ),
//
//                "5 hours ago",
//                media = ImageMedia("https://i.redd.it/vr2o7iiob5k91.jpg", 1080, 720),
//                "Sky: The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
//                id = "6",
//                likes = 6,
//                comments_count = 100
//            ),
//            PostModel(
//                PostedBy(
//                    "3",
//                    avatar = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Sergio_Ramos_Interview_2021.jpg/280px-Sergio_Ramos_Interview_2021.jpg",
//                    "Salem Gamal"
//                ),
//
//                "yesterday",
//                media = ImageMedia("https://i.redd.it/vr2o7iiob5k91.jpg", 1080, 720),
//                "Sky:The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
//                id = "7",
//                likes = 1,
//                comments_count = 2
//            ),
//            PostModel(
//                PostedBy(
//                    "4",
//                    avatar = "https://cdn.24.co.za/files/Cms/General/d/2694/af1c6139c4a54413844c145b2e67dee4.jpg",
//                    "Mohamed Reda"
//                ),
//
//                "2 hours ago",
//                media = ImageMedia("https://i.redd.it/vr2o7iiob5k91.jpg", 1080, 720),
//                "Sky:The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
//                id = "8",
//                likes = 7,
//                comments_count = 1
//            ),
//            PostModel(
//                PostedBy(
//                    "4",
//                    avatar = "https://cdn.24.co.za/files/Cms/General/d/2694/af1c6139c4a54413844c145b2e67dee4.jpg",
//                    "Mohamed Reda"
//                ),
//
//                "2 hours ago",
//                media = ImageMedia("https://i.redd.it/vr2o7iiob5k91.jpg", 1080, 720),
//                "Sky:The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
//                id = "9",
//                likes = 7,
//                comments_count = 9
//            ),
//        )
//
//
//        postsUiState = postsUiState.copy(posts = oldList.map { posts ->
//            PostUiState(
//                postedBy = posts.postedBy,
//                id = posts.id,
//                isDropDownMenuActive = posts.isDropDownMenuActive,
//                isSavedToLocal = posts.saveToLocal,
//                likes = posts.likes,
//                comments_count = posts.comments_count,
//                isLiked = posts.isLiked,
//                postDescription = posts.postDescription,
//                media = posts.media,
//                createdAt = posts.createdAt,
//
//                changeLikeState = {
//                    postsUiState = postsUiState.copy(
//                        posts = postsUiState.posts.updateState(posts.id) { oldPost ->
//                            oldPost.copy(
//                                isLiked = oldPost.isLiked.not(),
//                                likes = if (oldPost.isLiked) oldPost.likes?.dec() else oldPost.likes?.inc()
//                            )
//                        }
//                    )
//
//                }, changeDropDownMenuState = {
//                    postsUiState = postsUiState.copy(
//                        posts = postsUiState.posts.updateState(posts.id) { oldPost ->
//                            oldPost.copy(isDropDownMenuActive = oldPost.isDropDownMenuActive.not())
//                        }
//                    )
//                },
//                SaveToLocal = {
//                    postsUiState = postsUiState.copy(
//                        posts = postsUiState.posts.updateState(posts.id) { oldPost ->
//                            oldPost.copy(isSavedToLocal = oldPost.isSavedToLocal.not())
//                        }
//                    )
//                }
//            )
//
//        }
//        )
    }


    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.SearchTextChanged -> {
                postsUiState = postsUiState.copy(textSearch = event.text)
            }

            is HomeUiEvent.Search -> {
                search()
            }

            is HomeUiEvent.SearchBarActive -> {
                postsUiState = postsUiState.copy(isSearchBarActive = true)
            }

            is HomeUiEvent.AddToHistory -> {
                postsUiState.searchHistory.add(event.searchText)
            }

            is HomeUiEvent.ClearOrCloseSearchBar -> {
                if (postsUiState.textSearch.isBlank()) {
                    postsUiState = postsUiState.copy(isSearchBarActive = false)
                }
                postsUiState = postsUiState.copy(textSearch = "")

            }

        }
    }
    sealed class UiEvent {
        data class ShowMessage(var message: String) : UiEvent()
    }
}