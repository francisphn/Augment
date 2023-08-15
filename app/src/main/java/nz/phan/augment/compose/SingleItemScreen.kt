

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
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Face
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
import androidx.compose.ui.text.style.TextAlign
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
fun SingleItem(context: Context, model: Model) {

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
            Row(Modifier.padding(vertical = 20.dp)) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "Dropdown down icon"
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
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = model.imageId),
                    contentDescription = "The image of ${model.title}",
                    modifier = Modifier.width(140.dp).height(140.dp).clip(
                        RoundedCornerShape(100)
                    ).background(Color.White)
                )
            }
        }

        item {
            Text(
                text = model.title,
                style = Typography.displayLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center
            )
        }

        item {
            Text(
                text = model.movieName,
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
                    onClick = { },
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
    }
}




@Preview(showBackground = true)
@Composable
fun SingleItemPreview() {
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

        SingleItem(context, model = Model(1L, "Buzz Lightyear", "Toy Story", R.drawable.buzz_lightyear_thumbnail))
    }
}