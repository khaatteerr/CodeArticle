package com.compose.codearticle.presentaion.screens.publishScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.codearticle.presentaion.theme.Ubuntu

@Composable
fun PublishScreenDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onDiscard: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = Modifier

            .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
            .fillMaxWidth(0.95f),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {

            Text(
                text = "Save for later?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.inversePrimary,
                fontFamily = Ubuntu
            )
            Text(
                text = "The post you started will be here when you return",
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.inversePrimary,
                fontFamily = Ubuntu
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Go back",
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.8f),
                    fontFamily = Ubuntu,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .clickable {
                            onDismiss()
                        }
                        .padding(15.dp)
                )
                Box(modifier = Modifier.weight(1f))

                Text(
                    text = "Discard",
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.8f),
                    fontFamily = Ubuntu,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .clickable {
                            onDismiss()
                            onDiscard()
                        }
                        .padding(15.dp)
                )

                Text(
                    text = "Save",
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.inversePrimary.copy(0.8f),
                    fontFamily = Ubuntu,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .clickable {
                            onConfirm()
                        }
                        .padding(15.dp)
                )

            }

        }
    }
}