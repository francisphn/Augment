package nz.phan.augment.compose.screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nz.phan.augment.R
import nz.phan.augment.compose.shared.AnimalProfileImage
import nz.phan.augment.data.modelResources
import nz.phan.augment.entity.Model
import nz.phan.augment.entity.toModel
import nz.phan.augment.ui.theme.AugmentTheme
import nz.phan.augment.ui.theme.Blue50
import nz.phan.augment.ui.theme.Blue500
import nz.phan.augment.ui.theme.Typography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Catalogue(context: Context, itemAction: (Model) -> Unit, models: List<Model>, settingsAction: () -> Unit) {

    val initialCategory = stringResource(R.string.all_animals_label)

    var interactedElement by remember { mutableStateOf<Long?>(null) }
    var dropdownOpen by remember { mutableStateOf(false) }
    var category by remember { mutableStateOf(initialCategory) }
    var displayingModels by remember { mutableStateOf(models.toList()) }

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { settingsAction.invoke() }, containerColor = Blue50) {
                Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Icon(Icons.Outlined.Add, contentDescription = stringResource(R.string.add), modifier = Modifier.padding(end = 5.dp), tint = Color.DarkGray)

                    Text(text = stringResource(R.string.your_own_animal), style = Typography.labelMedium)
                }
            }
                               },
        content = {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(horizontal = 20.dp)) {
                item {
                    Text(
                        text = stringResource(R.string.catalogue_screen_title),
                        style = Typography.displayLarge,
                        modifier = Modifier.padding(top = 50.dp)
                    )
                }

                stickyHeader {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                            .padding(top = 30.dp))
                    {
                        Column {

                            Button(
                                onClick = {  dropdownOpen = true },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Blue500,
                                    contentColor = Color.White
                                )
                            ) {
                                Row( verticalAlignment = Alignment.CenterVertically ) {
                                    Text(text = stringResource(R.string.filter_prefix, category), style = Typography.labelMedium.plus(
                                        TextStyle(Color.White)
                                    ))

                                    Icon(
                                        Icons.Outlined.KeyboardArrowDown,
                                        contentDescription = stringResource(R.string.dropdown_down_icon_alt_text),
                                        modifier = Modifier.clickable { settingsAction.invoke() }
                                    )
                                }
                            }

                            DropdownMenu(
                                modifier = Modifier
                                    .background(Color.White)
                                    .fillMaxWidth(0.75F),
                                expanded = dropdownOpen,
                                onDismissRequest = { dropdownOpen = false },
                                offset = DpOffset(10.dp, 10.dp)
                            ) {
                                (listOf(initialCategory) + models.map { it -> it.category }.distinct())
                                    .forEach {
                                        DropdownMenuItem(
                                            modifier = if (it == category) Modifier.background(Blue50) else Modifier,
                                            onClick = {
                                                dropdownOpen = false
                                                category = it
                                                displayingModels = models.filter { m -> m.category == it || it == initialCategory }
                                            },
                                            text = { Text(text = it, style = Typography.bodyMedium.plus(TextStyle(color = Color.DarkGray,
                                                fontWeight = (if (it == category) FontWeight.Bold else FontWeight.Normal) ))) }
                                        )
                                    }
                            }
                        }
                    }
                }

                items(displayingModels) {
                    Card(
                        shape = RoundedCornerShape(size = 5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (displayingModels.indexOf(it) % 2 == 0) Color.White else Blue50,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clip(
                                RoundedCornerShape(size = 5.dp)
                            )
                            .clickable {
                                interactedElement = it.id
                                itemAction(it)
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(Modifier.padding(all = 10.dp)) {
                                Text(
                                    text = it.name,
                                    color = Color.DarkGray,
                                    style = Typography.labelMedium,
                                )

                                Text(
                                    text = it.category,
                                    color = Color.DarkGray,
                                    style = Typography.labelSmall,
                                )
                            }

                            Box {
                                AnimalProfileImage(
                                    it = it,
                                    backgroundColour = if (displayingModels.indexOf(it) % 2 != 0)
                                        Color.White else Blue50,
                                    size = 80.dp
                                )
                            }
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.copyright_notice_title).uppercase(),
                            style = Typography.bodyLarge.plus( TextStyle(fontWeight = FontWeight.Bold ))
                        )

                        Text(
                            text = stringResource(R.string.copyright_notice_text),
                            style = Typography.bodyMedium,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .padding(bottom = 100.dp)
                                .fillMaxWidth()
                        )
                    }
                }

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CataloguePreview() {
    val context = LocalContext.current
    val models = modelResources.map { it.toModel(action = { r -> stringResource(id = r) } ) }

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

        NavHost(navController = navController, startDestination = "catalogueScreen") {
            composable("catalogueScreen") {
                Catalogue(context = LocalContext.current,
                    itemAction = { navController.navigate("singleItemScreen") },
                    models = models.toList(),
                    settingsAction = { })
            }
        }
    }
}