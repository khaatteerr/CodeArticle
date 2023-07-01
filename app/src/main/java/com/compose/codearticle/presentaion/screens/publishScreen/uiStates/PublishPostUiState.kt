package com.compose.codearticle.presentaion.screens.publishScreen.uiStates

import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PublishPostUiState(
    val isLoading :Boolean = false,
    var id: String = "-1",
    val postDescription: InputFieldUiState = InputFieldUiState(),
    var imageUri: Uri ?= null,
    var dateTime: String = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", Locale.getDefault()).format(
        Date()
    ),
    var isImageVisible: Boolean = false,
    var isDialogShown:Boolean = false,
    val postButtonEnable:Boolean = false,
    val postButtonColor: Int = Color.DarkGray.toArgb()
)
