package nz.phan.augment.compose.shared

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import nz.phan.augment.R
import nz.phan.augment.entity.Model

@Composable
fun AnimalProfileImage(it: Model, backgroundColour: Color, size: Dp) {
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
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    } else {
        Log.d("ds", it.imageUriAsString!!)

        SubcomposeAsyncImage(
            model = it.imageUriAsString as String,
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
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator()
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnimalProfileImagePreview() {
    AnimalProfileImage(it = Model(
        id = 11L,
        category = stringResource(R.string.user),
        description = "",
        name = stringResource(R.string.tiger),
        imageUriAsString = stringResource(R.string.https_upload_wikimedia_org_wikipedia_commons_thumb_5_52_echinoderm_collage_2_jpg_220px_echinoderm_collage_2_jpg)
    ), backgroundColour = Color.White, size = 140.dp)
}