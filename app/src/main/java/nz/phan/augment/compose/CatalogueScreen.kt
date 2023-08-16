

package nz.phan.augment.compose


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nz.phan.augment.R
import nz.phan.augment.entity.Model
import nz.phan.augment.ui.theme.AugmentTheme
import nz.phan.augment.ui.theme.Blue50
import nz.phan.augment.ui.theme.Blue500
import nz.phan.augment.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Catalogue(context: Context, onNavigate: ((Int) -> Unit)?) {
    val models = arrayOf(
        Model(1L, "Buzz Lightyear", "Toy Story", R.drawable.buzz_lightyear_thumbnail),
        Model(2L, "Rapunzel", "Tangled", R.drawable.rapunzel_thumbnail),
        Model(3L,"Woody", "Toy Story", R.drawable.buzz_lightyear_thumbnail),
        Model(4L, "Jessie", "Toy Story", R.drawable.buzz_lightyear_thumbnail),
        Model(5L, "Wheezy", "Toy Story", R.drawable.buzz_lightyear_thumbnail),
        Model(6L, "Mr Potato Head", "Toy Story", R.drawable.buzz_lightyear_thumbnail),
        Model(7L, "Mrs Potato Head", "Toy Story", R.drawable.buzz_lightyear_thumbnail),
        Model(8L, "Hamm", "Toy Story", R.drawable.buzz_lightyear_thumbnail),
        Model(9L, "Elsa", "Frozen", R.drawable.buzz_lightyear_thumbnail),
        Model(9L, "Anna", "Frozen", R.drawable.buzz_lightyear_thumbnail),
        Model(9L, "Olaf", "Frozen", R.drawable.olaf_frozen_thumbnail),
        Model(9L, "Kristoff", "Frozen", R.drawable.buzz_lightyear_thumbnail),

        )

    val initialCategory = "All Disney movies"

    var interactedElement by remember { mutableStateOf<Long?>(null) }
    var dropdownOpen by remember { mutableStateOf(false) }

    var category by remember { mutableStateOf(initialCategory) }

    var isOpen by remember { mutableStateOf(false) } // initial value

    val openCloseOfDropDownList: (Boolean) -> Unit = {
        isOpen = it
    }
    val userSelectedString: (String) -> Unit = {
        category = it
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp)) {
        item {
            Box {
                Text(
                    text = "Catalogue",
                    style = Typography.displayLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                )
            }
        }

        stickyHeader {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp))
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
                            Text(text = "Viewing: $category", style = Typography.labelMedium)

                            Icon(
                                Icons.Outlined.KeyboardArrowDown,
                                contentDescription = "Dropdown down icon"
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
                        (listOf(initialCategory) + models.map { it -> it.movieName }.distinct())
                            .forEach {
                                DropdownMenuItem(
                                    modifier = if (it == category) Modifier.background(Blue50) else Modifier,
                                    onClick = {
                                        dropdownOpen = false
                                        category = it
                                    },
                                    text = { Text(text = it, style = Typography.bodyMedium.plus(TextStyle(color = Color.DarkGray,
                                        fontWeight = (if (it == category) FontWeight.Bold else FontWeight.Normal) ))) }
                                )
                            }
                    }
                }
            }
        }

        items(models.filter { it.movieName == category || category == initialCategory }) {
            Card(
                shape = RoundedCornerShape(size = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (models.indexOf(it) % 2 == 0) Color.White else Blue50,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .clickable {
                        interactedElement = it.id
                        onNavigate?.let { it1 -> it1(0) }
                    }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.padding(all = 10.dp)) {
                        Text(
                            text = it.title,
                            color = Color.DarkGray,
                            style = Typography.labelMedium,
                        )

                        Text(
                            text = it.movieName,
                            color = Color.DarkGray,
                            style = Typography.labelSmall,
                        )
                    }

                    Box {
                        Image(
                            painter = painterResource(id = it.imageId),
                            contentDescription = "The thumbnail image of ${it.title}",
                            modifier = Modifier.padding(all = 10.dp).width(40.dp).height(40.dp).clip(
                                RoundedCornerShape(100)
                            ).background(if (models.indexOf(it) % 2 != 0) Color.White else Blue50)
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
                    text = "Copyright notice".uppercase(),
                    style = Typography.bodyLarge.plus( TextStyle(fontWeight = FontWeight.Bold ))
                )

                Text(
                    text = "All Disney character images are 2023 © Disney, which are reused under the Fair Use doctrine for the purpose of education.",
                    style = Typography.bodyMedium,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                )

                Button(
                    onClick = {
                        val uri = Uri.parse("https://www.dallascounsel.com/disney-ip-licensing-and-trademark-infringement")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        ContextCompat.startActivity(context, intent, null)
                    },
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue500,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "More details", style = Typography.labelMedium)
                }


            }
        }

    }
}


@Composable
fun DropDownList(
    requestToOpen: Boolean = false,
    list: List<String>,
    request: (Boolean) -> Unit,
    selectedString: (String) -> Unit
) {
    DropdownMenu(
        modifier = Modifier.fillMaxWidth(),
        expanded = requestToOpen,
        onDismissRequest = { request(false) },
    ) {
        list.forEach {
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                onClick = { request(false) },
                text = { Text(text = it) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CataloguePreview() {
    val context = LocalContext.current

    AugmentTheme {
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

        Catalogue(context, onNavigate = null)
    }
}