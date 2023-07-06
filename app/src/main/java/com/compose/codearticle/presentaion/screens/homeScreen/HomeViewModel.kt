package com.compose.codearticle.presentaion.screens.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.HomeUiEvent
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostModel
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostUiState
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostedBy
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.PostsUiState
import com.compose.codearticle.presentaion.utilities.updateState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    var postsUiState by mutableStateOf(PostsUiState(isLoading = true))

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
        val oldList = listOf(
            PostModel(
                PostedBy(
                    "1",
                    avatar = "https://nationaltoday.com/wp-content/uploads/2022/04/Cristiano-Ronaldo-Birthday.jpg",
                    "Ahmed Khater"
                ),
                "2 hours ago",
                "https://i.redd.it/vr2o7iiob5k91.jpg",
                "Sky:\n" +
                        "The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
                id = "1"
            ),
            PostModel(
                PostedBy(
                    "2",
                    avatar = "https://cloudfront-us-east-2.images.arcpublishing.com/reuters/XEEPZPVPD5O4RO76334OJYCQ4M.jpg",
                    "Khaled Abady"
                ),

                "5 hours ago",
                "https://images.unsplash.com/photo-1580615633247-58fedbee9197?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw0NzQ4fHx2ZXJ0aWNhbHxlbnwwfDF8fHwxNjYxNjI5MDIx&ixlib=rb-1.2.1&q=80&w=1080",
                "Sky:\n" +
                        "The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
                id = "2"
            ),
            PostModel(
                PostedBy(
                    "3",
                    avatar = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Sergio_Ramos_Interview_2021.jpg/280px-Sergio_Ramos_Interview_2021.jpg",
                    "Salem Gamal"
                ),

                "yesterday",
                "https://images.unsplash.com/photo-1661355103273-e3fc8ad60c68?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw0NzQ0fHx2ZXJ0aWNhbHxlbnwwfDF8fHwxNjYxNjI5MDIx&ixlib=rb-1.2.1&q=80&w=1080",
                "Sky:\n" +
                        "The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
                id = "3"
            ),
            PostModel(
                PostedBy(
                    "4",
                    avatar = "https://cdn.24.co.za/files/Cms/General/d/2694/af1c6139c4a54413844c145b2e67dee4.jpg",
                    "Mohamed Reda"
                ),

                "2 hours ago",
                "https://images.unsplash.com/photo-1610736702440-9dfab24cd7da?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw3Njd8fE1vYmlsZSUyMFdhbGxwYXBlcnN8ZW58MHwxfHx8MTY2MTYzMTcyMw&ixlib=rb-1.2.1&q=80&w=1080",
                "Sky:\n" +
                        "The sky, a vast expanse stretching endlessly above, is a mesmerizing tapestry of celestial wonders. It encompasses the boundless realm that connects us to the universe, revealing its awe-inspiring secrets. During the day, the sky unfolds in a symphony of vibrant hues, from the crisp azure of a cloudless sky to the fiery embrace of the sun's golden rays. Wispy clouds dance across the canvas, casting playful shadows upon the earth below. As ",
                id = "4"
            )
        )


        postsUiState = postsUiState.copy(posts = oldList.map { posts ->
            PostUiState(
                postedBy = posts.postedBy,
                id = posts.id,
                isDropDownMenuActive = posts.isDropDownMenuActive,
                isSavedToLocal = posts.saveToLocal,
                likes = posts.likes,
                comments = posts.comments,
                isLiked = posts.isLiked,
                postDescription = posts.postDescription,
                postImage = posts.postImage,
                createdAt = posts.createdAt,

                changeLikeState = {
                    postsUiState = postsUiState.copy(
                        posts = postsUiState.posts.updateState(posts.id) { oldPost ->
                            oldPost.copy(isLiked = oldPost.isLiked.not())
                        }
                    )
                }, changeDropDownMenuState = {
                    postsUiState = postsUiState.copy(
                        posts = postsUiState.posts.updateState(posts.id) { oldPost ->
                            oldPost.copy(isDropDownMenuActive = oldPost.isDropDownMenuActive.not())
                        }
                    )
                },
                SaveToLocal = {
                    postsUiState = postsUiState.copy(
                        posts = postsUiState.posts.updateState(posts.id) { oldPost ->
                            oldPost.copy(isSavedToLocal = oldPost.isSavedToLocal.not())
                        }
                    )
                }
            )

        }
        )
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
}