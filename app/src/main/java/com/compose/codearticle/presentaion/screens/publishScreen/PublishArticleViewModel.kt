package com.compose.codearticle.presentaion.screens.publishScreen

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.codearticle.domain.usecases.ValidatePostDescriptionUseCase
import com.compose.codearticle.presentaion.screens.publishScreen.uiStates.PublishPostUiState
import com.compose.codearticle.presentaion.screens.publishScreen.uiStates.PublishPostUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostArticleViewModel @Inject constructor(
    var validatePostDescriptionUseCase: ValidatePostDescriptionUseCase
) : ViewModel() {

    var postScreenState by mutableStateOf(PublishPostUiState())
        private set


    private var _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()


    private fun onDialogSave() {

    }


    private fun postArticle() {
        viewModelScope.launch {
            val postDescriptionValidationResult =
                validatePostDescriptionUseCase(postScreenState.postDescription.text)

            val hasValidationError =
                listOf(postDescriptionValidationResult).any { it.error != null }

            if (hasValidationError) {
                postScreenState = postScreenState.copy(
                    postDescription = postScreenState.postDescription.copy(
                    errorMessage = postDescriptionValidationResult.error
                    )
                )
            } else {
                try {
                    postScreenState = postScreenState.copy(isLoading = true)

                    //Post Article
                    postScreenState = postScreenState.copy(isLoading = false)
                    _eventFlow.emit(UiEvent.PostArticleSuccess)
                } catch (e: Exception) {
                    _eventFlow.emit(UiEvent.ShowMessage(e.message.toString()))
                    postScreenState = postScreenState.copy(isLoading = false)
                }

            }
        }
    }

    fun onEvent(event: PublishPostUiEvent) {
        when (event) {
            is PublishPostUiEvent.DescriptionTextChanged -> postScreenState =
                postScreenState.copy(
                    postDescription = postScreenState.postDescription.copy(
                        text = event.text,
                        errorMessage = null
                    )
                )

            is PublishPostUiEvent.Publish -> {
                postArticle()
            }

            is PublishPostUiEvent.CancelPost -> {
              postScreenState = postScreenState.copy(isDialogShown = true)
            }

            is PublishPostUiEvent.DismissDialog -> {
                postScreenState = postScreenState.copy(isDialogShown = false)
            }

            is PublishPostUiEvent.SaveDialog -> {
                onDialogSave()
            }

            is PublishPostUiEvent.SelectedImage -> {
                if (event.image != Uri.EMPTY) {
                    postScreenState = postScreenState.copy(imageUri = event.image)
                    postScreenState = postScreenState.copy(isImageVisible = true)
                }
            }

            is PublishPostUiEvent.DeleteImage -> {
                postScreenState = postScreenState.copy(isImageVisible = false).also {
                    postScreenState = postScreenState.copy(imageUri = Uri.EMPTY)
                }
            }

            is PublishPostUiEvent.IsPublishEnable -> {
                if (postScreenState.isImageVisible && postScreenState.postDescription.text.isNotBlank()) {
                    postScreenState = postScreenState.copy(postButtonEnable = true)
                    postScreenState = postScreenState.copy(postButtonColor = event.color[0])
                } else {
                    postScreenState = postScreenState.copy(postButtonEnable = false)
                    postScreenState = postScreenState.copy(postButtonColor = event.color[1])
                }
            }
        }

    }
}


sealed class UiEvent {
    object PostArticleSuccess : UiEvent()
    data class ShowMessage(var error: String) : UiEvent()
}