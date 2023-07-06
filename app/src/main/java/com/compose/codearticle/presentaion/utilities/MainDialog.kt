package com.compose.codearticle.presentaion.utilities

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MainDialog(
    content: @Composable () -> Unit
) {

    Column {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                usePlatformDefaultWidth = false,
                dismissOnClickOutside = true
            )
        ) {
            content()
        }

    }

}