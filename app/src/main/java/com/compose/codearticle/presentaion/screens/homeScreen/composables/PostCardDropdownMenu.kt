package com.compose.codearticle.presentaion.screens.homeScreen.composables

import androidx.compose.foundation.background
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun PostCardDropdownMenu(
    onExpand: Boolean,
    onDismiss: () -> Unit
) {
    DropdownMenu(
        expanded = onExpand,
        onDismissRequest = { onDismiss() },
        modifier = Modifier.background(MaterialTheme.colorScheme.secondary)
    ) {
        DropdownMenuItem(
            text = { Text("Refresh") },
            onClick = { /* Handle refresh! */ }
        )
        DropdownMenuItem(
            text = { Text("Settings") },
            onClick = { /* Handle settings! */ }
        )
        Divider()
        DropdownMenuItem(
            text = { Text("Send Feedback") },
            onClick = { /* Handle send feedback! */ }
        )

    }
}
