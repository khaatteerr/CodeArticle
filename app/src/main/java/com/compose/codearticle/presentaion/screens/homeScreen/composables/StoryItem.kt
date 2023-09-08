package com.compose.codearticle.presentaion.screens.homeScreen.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.compose.codearticle.presentaion.screens.homeScreen.uiStates.StoryUiState
import com.compose.codearticle.presentaion.theme.Ubuntu
import com.compose.codearticle.presentaion.theme.White0_7
import com.exyte.animatednavbar.utils.toPxf
import com.skydoves.cloudy.Cloudy
import com.skydoves.cloudy.rememberCloudyState
import kotlinx.serialization.json.JsonNull.content
import java.nio.file.WatchEvent

@Composable
fun StoryItem(storyUiState: StoryUiState) {
    Box(
        Modifier
            .shadow(
                10.dp,
                spotColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .width(120.dp)
            .height(150.dp)
    ) {
        AsyncImage(
            model = "https://i.redd.it/vr2o7iiob5k91.jpg",
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        val shape = remember {
            GenericShape { size: Size, _: LayoutDirection ->
                this.roundedRectanglePath(
                    size = size,
                    cornerRadius = 30f,
                    fabRadius = 80f
                )
            }
        }

        AsyncImage(
            model = "https://nationaltoday.com/wp-content/uploads/2022/04/Cristiano-Ronaldo-Birthday.jpg",
            contentDescription = null,
            modifier = Modifier
                .offset(0.dp, (-40).dp)
                .size(40.dp)
                .clip(CircleShape)
                .align(Alignment.BottomCenter)

        )


            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .clip(shape)
                    .fillMaxWidth()
                    .height(60.dp).background(Color.LightGray.copy(0.5f))

            )

                Text(
                    text = storyUiState.postedBy!!.username!!,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(
                            Alignment.BottomCenter
                        ),
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = Ubuntu,
                    fontWeight = FontWeight.Bold
                )

        }


}

fun Path.roundedRectanglePath(
    size: Size,
    cornerRadius: Float,
    fabRadius: Float,
) {

    val centerX = size.width / 2
    val x0 = centerX - fabRadius * 1.15f
    val y0 = 0f

    // offset of the first control point (top part)
    val topControlX = x0 + fabRadius * .5f
    val topControlY = y0

    // offset of the second control point (bottom part)
    val bottomControlX = x0
    val bottomControlY = y0 + fabRadius

    // first curve
    // set the starting point of the curve (P2)
    val firstCurveStart = Offset(x0, y0)

    // set the end point for the first curve (P3)
    val firstCurveEnd = Offset(centerX, fabRadius * 1f)

    // set the first control point (C1)
    val firstCurveControlPoint1 = Offset(
        x = topControlX,
        y = topControlY
    )

    // set the second control point (C2)
    val firstCurveControlPoint2 = Offset(
        x = bottomControlX,
        y = bottomControlY
    )


    // second curve
    // end of first curve and start of second curve is the same (P3)
    val secondCurveStart = Offset(
        x = firstCurveEnd.x,
        y = firstCurveEnd.y
    )

    // end of the second curve (P4)
    val secondCurveEnd = Offset(
        x = centerX + fabRadius * 1.15f,
        y = 0f
    )

    // set the first control point of second curve (C4)
    val secondCurveControlPoint1 = Offset(
        x = secondCurveStart.x + fabRadius,
        y = bottomControlY
    )

    // set the second control point (C3)
    val secondCurveControlPoint2 = Offset(
        x = secondCurveEnd.x - fabRadius / 2,
        y = topControlY
    )


    // Top left arc
    val radius = cornerRadius * 2

    arcTo(
        rect = Rect(
            left = 0f,
            top = 0f,
            right = radius,
            bottom = radius
        ),
        startAngleDegrees = 180.0f,
        sweepAngleDegrees = 90.0f,
        forceMoveTo = false
    )



    lineTo(x = firstCurveStart.x, y = firstCurveStart.y)

    // bezier curve with (P2, C1, C2, P3)
    cubicTo(
        x1 = firstCurveControlPoint1.x,
        y1 = firstCurveControlPoint1.y,
        x2 = firstCurveControlPoint2.x,
        y2 = firstCurveControlPoint2.y,
        x3 = firstCurveEnd.x,
        y3 = firstCurveEnd.y
    )

    // bezier curve with (P3, C4, C3, P4)
    cubicTo(
        x1 = secondCurveControlPoint1.x,
        y1 = secondCurveControlPoint1.y,
        x2 = secondCurveControlPoint2.x,
        y2 = secondCurveControlPoint2.y,
        x3 = secondCurveEnd.x,
        y3 = secondCurveEnd.y
    )

    lineTo(x = size.width - cornerRadius, y = 0f)

    // Top right arc
    arcTo(
        rect = Rect(
            left = size.width - radius,
            top = 0f,
            right = size.width,
            bottom = radius
        ),
        startAngleDegrees = -90.0f,
        sweepAngleDegrees = 90.0f,
        forceMoveTo = false
    )

    lineTo(x = 0f + size.width, y = size.height - cornerRadius)

    // Bottom right arc
    arcTo(
        rect = Rect(
            left = size.width - radius,
            top = size.height - radius,
            right = size.width,
            bottom = size.height
        ),
        startAngleDegrees = 0f,
        sweepAngleDegrees = 90.0f,
        forceMoveTo = false
    )

    lineTo(x = cornerRadius, y = size.height)

    // Bottom left arc
    arcTo(
        rect = Rect(
            left = 0f,
            top = size.height - radius,
            right = radius,
            bottom = size.height
        ),
        startAngleDegrees = 90.0f,
        sweepAngleDegrees = 90.0f,
        forceMoveTo = false
    )

    lineTo(x = 0f, y = cornerRadius)
    close()
}