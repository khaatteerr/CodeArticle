package com.compose.codearticle.presentaion.screens.publishScreen.uiStates

import android.net.Uri

sealed class PublishPostUiEvent {

    data class DescriptionTextChanged(var text: String) : PublishPostUiEvent()
    object Publish : PublishPostUiEvent()
    object Cancel :PublishPostUiEvent()
    object DismissDialog:PublishPostUiEvent()
    object SaveDialog:PublishPostUiEvent()
    data class SelectedImage(var image: Uri) :PublishPostUiEvent()
    object DeleteImage : PublishPostUiEvent()
    data class IsPublishEnable(var color: List<Int>) : PublishPostUiEvent()
}
