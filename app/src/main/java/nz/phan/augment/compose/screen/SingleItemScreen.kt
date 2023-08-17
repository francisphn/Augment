

package nz.phan.augment.compose.screen


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nz.phan.augment.compose.shared.drawColoredShadow
import nz.phan.augment.ArActivity
import nz.phan.augment.R
import nz.phan.augment.compose.shared.AnimalProfileImage
import nz.phan.augment.compose.shared.BackButton
import nz.phan.augment.data.modelResources
import nz.phan.augment.entity.Model
import nz.phan.augment.entity.toModel
import nz.phan.augment.ui.theme.AugmentTheme
import nz.phan.augment.ui.theme.Blue50
import nz.phan.augment.ui.theme.Blue500
import nz.phan.augment.ui.theme.Typography

@Composable
fun SingleItem(context: Context, model: Model, backAction: () -> Unit,
               suggestedCharacterAction: (Model) -> Unit,
               models: List<Model>) {

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp)) {
        item {
            BackButton { backAction.invoke() }
        }

        item {
            BoxWithConstraints {
                if (maxWidth < 400.dp) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            AnimalProfileImage(
                                it = model,
                                backgroundColour = Color.White,
                                size = 240.dp
                            )
                        }

                        Text(
                            text = model.name,
                            style = Typography.displayLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = model.category,
                            style = Typography.titleLarge,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        if (model.arPlacement != null) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    onClick = {
                                        val intent = Intent(context, ArActivity::class.java)
                                        intent.putExtra("modelId", model.id)
                                        context.startActivity(intent)
                                    },
                                    modifier = Modifier.padding(top = 30.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Blue500,
                                        contentColor = Color.White
                                    ),
                                ) {
                                    Row( verticalAlignment = Alignment.CenterVertically ) {
                                        Icon(
                                            Icons.Rounded.Face,
                                            contentDescription = stringResource(R.string.human_face_icon_alt_text)
                                        )


                                        Text(text = stringResource(R.string.view_in_ar_button_label),
                                            style = Typography.labelMedium.plus(
                                                TextStyle(Color.White)
                                            ), modifier = Modifier.padding(start = 10.dp))
                                    }
                                }
                            }
                        }
                    }


                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AnimalProfileImage(
                            it = model,
                            backgroundColour = Color.White,
                            size = 240.dp
                        )

                        Column(modifier = Modifier.padding(start = 20.dp)) {
                            Text(
                                text = model.name,
                                style = Typography.displayLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp)
                            )

                            Text(
                                text = model.category,
                                style = Typography.titleLarge,
                                modifier = Modifier
                                    .fillMaxWidth(),
                            )

                            if (model.arPlacement != null) {
                                Row {
                                    Button(
                                        onClick = {
                                            val intent = Intent(context, ArActivity::class.java)
                                            intent.putExtra("modelId", model.id)
                                            context.startActivity(intent)
                                        },
                                        modifier = Modifier.padding(top = 30.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Blue500,
                                            contentColor = Color.White
                                        ),
                                    ) {
                                        Row( verticalAlignment = Alignment.CenterVertically ) {
                                            Icon(
                                                Icons.Rounded.Face,
                                                contentDescription = stringResource(R.string.human_face_icon_alt_text)
                                            )


                                            Text(text = stringResource(R.string.view_in_ar_button_label),
                                                style = Typography.labelMedium.plus(
                                                    TextStyle(Color.White)
                                                ), modifier = Modifier.padding(start = 10.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }



        item {
            Text(
                text = model.description,
                style = Typography.bodyMedium,
                modifier = Modifier.padding(vertical = 30.dp)
            )
        }

        if (model.wikipediaUri != "") {
            item {
                Text(
                    text = stringResource(R.string.read_more_on_wikipedia_button_label),
                    style = Typography.bodyMedium.plus(TextStyle(textDecoration = TextDecoration.Underline)),
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(model.wikipediaUri))
                            context.startActivity(intent)
                        }
                )
            }
        }

        item {
            Column {
                Text(
                    text = stringResource(R.string.suggestion_label),
                    style = Typography.titleLarge.plus(TextStyle(fontWeight = FontWeight.Bold)),
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(0.5F)
                )

                val suggestedModels = models.filter { m -> m.id != model.id }.shuffled().take(3)
                LazyRow(Modifier.padding(bottom = 100.dp)) {
                    items(suggestedModels) {
                        Column(
                            modifier = Modifier
                                .padding(5.dp)

                                .clip(
                                    RoundedCornerShape(size = 5.dp)
                                )
                                .width(200.dp)
                                .height(100.dp)
                                .background(Color.White)
                                .drawColoredShadow()
                                .clickable {
                                    suggestedCharacterAction(it)
                                }
                        ) {

                            Row(
                                modifier = Modifier
                                    .padding(1.dp)
                                    .clip(
                                        RoundedCornerShape(size = 5.dp)
                                    )
                                    .width(200.dp)
                                    .height(100.dp)
                                    .background(Color.White),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AnimalProfileImage(
                                    it = it,
                                    backgroundColour = if (suggestedModels.indexOf(it) % 2 == 0)
                                        Color.White else Blue50,
                                    size = 70.dp
                                )

                                Text(
                                    text = it.name,
                                    style = Typography.labelMedium,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                        .padding(bottom = 10.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleItemPreview() {
    val context = LocalContext.current

    AugmentTheme {
        val models = modelResources.map { it -> it.toModel(action = { r -> stringResource(id = r) } ) }

        val navController = rememberNavController()
        val systemUiController = rememberSystemUiController()

        SideEffect {
            systemUiController.setStatusBarColor(
                color = Color.Black
            )
            systemUiController.setNavigationBarColor(
                color = Color.White,
                darkIcons = true
            )
        }
        NavHost(navController = navController, startDestination = "singleItemScreen") {
            composable("singleItemScreen") {
                SingleItem(context,
                    modelResources.first().toModel { stringResource(id = it) },
                    backAction = { navController.popBackStack() },
                    suggestedCharacterAction = { },
                    models = models.toList())
            }
        }


    }
}

