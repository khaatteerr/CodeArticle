package com.compose.codearticle.presentaion.screens.homeScreen.uiStates

data class PostUiState(
    val postedBy: PostedBy? = null,
    val createdAt: String = "",
    var postDescription: String = "",
    var id: String = "-1",
    var isFollowing: Boolean = false,
    var isLiked: Boolean = false,
    var likes: Int? = null,
    val comments_count:  Int? = null,
    val media: ImageMedia,
    val changeLikeState: () -> Unit,
    var isSavedToLocal: Boolean = false,
    var isDropDownMenuActive: Boolean = false,
    val changeDropDownMenuState: () -> Unit,
    val SaveToLocal: () -> Unit,
)

data class PostModel(
    val postedBy: PostedBy? = null,
    val createdAt: String = "",
    val media: ImageMedia,
    val postDescription: String = "",
    var id: String = "-1",
    var isLiked: Boolean = false,
    var likes: Int? = null,
    val comments_count: Int? = null,
    var saveToLocal: Boolean = false,
    var isDropDownMenuActive: Boolean = false,
)

data class ImageMedia(
    val postImage: String = "",
    val originalHeight: Int = 0,
    val originalWidth: Int = 1
)

data class PostedBy(
    val _id: String? = null,
    val avatar: String? = null,
    val username: String? = null
)

data class CommentedBy(
    val commentId: String? = null,
    val avatar: String? = null,
    val username: String? = null,
    val userId: String? = null,
    val content: String? = null
)
