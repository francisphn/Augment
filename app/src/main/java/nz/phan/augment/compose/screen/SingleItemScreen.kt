

package nz.phan.augment.compose.screen


import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nz.phan.augment.compose.shared.drawColoredShadow
import nz.phan.augment.ArActivity
import nz.phan.augment.compose.shared.noRippleClickable
import nz.phan.augment.data.models
import nz.phan.augment.entity.Model
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
            Row(
                Modifier
                    .padding(vertical = 20.dp)
                    .wrapContentSize()
                    .noRippleClickable { backAction.invoke() }) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "Back icon"
                )

                Text(
                    text = "Back",
                    style = Typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = model.imageId),
                    contentDescription = "The image of ${model.name}",
                    modifier = Modifier
                        .width(240.dp)
                        .height(240.dp)
                        .clip(
                            RoundedCornerShape(100)
                        )
                        .background(Color.White)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        item {
            Text(
                text = model.name,
                style = Typography.displayLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center
            )
        }

        item {
            Text(
                text = model.categoryName,
                style = Typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        item {
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
                            contentDescription = "Dropdown down icon"
                        )


                        Text(text = "View in AR", style = Typography.labelMedium, modifier = Modifier.padding(start = 10.dp))
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

        item {
            Text(
                text = "Read more on Wikipedia",
                style = Typography.bodyMedium,
                modifier = Modifier.padding(bottom = 30.dp)
            )
        }

        item {
            Column {
                Text(
                    text = "You may also be interested in...",
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
                                .width(100.dp)
                                .height(200.dp)
                                .background(Color.White)
                                .drawColoredShadow()
                                .clickable {
                                    suggestedCharacterAction(it)
                                }
                        ) {

                            Column(
                                modifier = Modifier
                                    .padding(1.dp)
                                    .clip(
                                        RoundedCornerShape(size = 5.dp)
                                    )
                                    .width(100.dp)
                                    .height(200.dp)
                                    .background(Color.White)
                            ) {
                                Image(
                                    painter = painterResource(id = it.imageId),
                                    contentDescription = "The thumbnail image of ${it.name}",
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .width(70.dp)
                                        .height(70.dp)
                                        .clip(
                                            RoundedCornerShape(100)
                                        )
                                        .background(if (suggestedModels.indexOf(it) % 2 == 0) Color.White else Blue50)
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop,
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
                    model = models.first(),
                    backAction = { navController.popBackStack() },
                    suggestedCharacterAction = { },
                    models = models.asList())
            }
        }


    }
}

