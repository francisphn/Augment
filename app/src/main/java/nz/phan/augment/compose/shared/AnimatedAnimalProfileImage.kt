package nz.phan.augment.compose.shared

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import nz.phan.augment.R
import nz.phan.augment.data.modelResources
import nz.phan.augment.entity.Model
import nz.phan.augment.entity.toModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AnimatedAnimalProfileImage(it: Model, backgroundColour: Color, size: Dp) {
    val rotationState = remember { mutableStateOf(0f) }

    val animationSpec = tween<Float>(durationMillis = 1200)

    // Animate the rotation
    val rotation by animateFloatAsState(
        targetValue = rotationState.value, label = "",
        animationSpec = animationSpec
    )

    var painter: AsyncImagePainter? = null

    if (it.imageUriAsString != null) {
        painter = rememberAsyncImagePainter(it.imageUriAsString)
    }


    if (it.imageResId != null) {
        Image(
            painter = painterResource(id = it.imageResId!!),
            contentDescription = stringResource(
                R.string.thumbnail_alt_text,
                it.name
            ),
            modifier = Modifier
                .padding(all = 10.dp)
                .width(size)
                .height(size)
                .clip(
                    RoundedCornerShape(100)
                )
                .background(backgroundColour)
                .fillMaxSize()
                .clickable {
                    // Increase the rotation by 360 (full circle) on tap
                    rotationState.value += 360f
                }
                .graphicsLayer {
                    // Apply the rotation
                    rotationZ = rotation
                },
            contentScale = ContentScale.Crop,
        )
    } else {
        Image(
            painter = painter!!,
            contentDescription = stringResource(
                R.string.thumbnail_alt_text,
                it.name
            ),
            modifier = Modifier
                .padding(all = 10.dp)
                .width(size)
                .height(size)
                .clip(
                    RoundedCornerShape(100)
                )
                .background(backgroundColour)
                .fillMaxSize()
                .clickable {
                    // Increase the rotation by 360 (full circle) on tap
                    rotationState.value += 360f
                }
                .graphicsLayer {
                    // Apply the rotation
                    rotationZ = rotation
                },
            contentScale = ContentScale.Crop,
//            loading = {
//                CircularProgressIndicator()
//            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedAnimalProfileImagePreview() {
    AnimatedAnimalProfileImage(it =
    Model(
        id = 11L,
        category = stringResource(R.string.user),
        description = "",
        name = stringResource(R.string.tiger),
        imageUriAsString = stringResource(R.string.https_upload_wikimedia_org_wikipedia_commons_thumb_5_52_echinoderm_collage_2_jpg_220px_echinoderm_collage_2_jpg)
    ),
        backgroundColour = Color.White, size = 140.dp)
}