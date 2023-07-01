package com.compose.codearticle.presentaion.screens.homeScreen.uiStates

data class PostUiState(
    val postedBy: PostedBy? = null,
    val createdAt: String = "",
    val postImage: String = "",
    val postDescription: String = "",
    var id: String = "-1",
    var isLiked: Boolean = false,
    val likes: List<String?>? = null,
    val comments: List<String?>? = null,
    var saveToLocal: Boolean = false,
    var isDropDownMenuActive: Boolean = false,
 )

data class PostedBy(
    val _id: String? = null,
    val avatar: String? = null,
    val username: String? = null
)
