package nz.phan.augment.compose.screen

import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nz.phan.augment.R
import nz.phan.augment.compose.shared.BackButton

import nz.phan.augment.data.models
import nz.phan.augment.ui.theme.AugmentTheme
import nz.phan.augment.ui.theme.Blue500
import nz.phan.augment.ui.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAnimal(thisAnimalId: Long,
              context: Context,
              backAction: () -> Unit,
              onSave: (nz.phan.augment.entity.Model) -> Unit) {
    val toastMessage = stringResource(R.string.your_animal_is_saved)

    var animalName by remember { mutableStateOf("") }
    var animalDescription by remember { mutableStateOf("") }
    var animalPhotoUrlAsString by remember { mutableStateOf("") }

    val defaultCategoryName = stringResource(R.string.user_provided_animal)

    val model by remember { mutableStateOf(nz.phan.augment.entity.Model(
        id = thisAnimalId,
        name = "",
        categoryName = defaultCategoryName,
        imageUriAsString = "",
        description = "",
    )) }

    data class Field(val fieldName: String, val value: String, val action: (String) -> Unit)

    val textFields = listOf(
        Field(stringResource(R.string.animal_name), animalName) {
            model.name = it.trim()
            animalName = it
                                         },
        Field(stringResource(R.string.animal_description), animalDescription) {
            model.description = it.trim()
            animalDescription = it
                                                       },
        Field(stringResource(R.string.https_link_to_an_internet_photo), animalPhotoUrlAsString ) {
            model.imageUriAsString = it
            animalPhotoUrlAsString = it
                                                                          },
    )
    
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp)) {
        item {
            BackButton { backAction.invoke() }
        }

        item {
            Text(
                text = stringResource(R.string.add_a_new_animal),
                style = Typography.displayLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            )
        }

        item {
            Text(
                modifier = Modifier.padding(top = 50.dp),
                text = stringResource(R.string.details).uppercase(),
                style = Typography.bodyLarge.plus( TextStyle(fontWeight = FontWeight.Bold ))
            )
        }

        items(textFields) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)) {
                Text(text = it.fieldName, modifier = Modifier.padding(bottom = 10.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = it.value,
                    onValueChange = it.action
                )
            }
        }

        item {
            Button(onClick = {
                val clipboard = ContextCompat.getSystemService(context, ClipboardManager::class.java) as ClipboardManager

                animalPhotoUrlAsString =
                    clipboard.primaryClip?.getItemAt(0)?.text?.toString().toString()
            }, modifier = Modifier.padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue500,
                    contentColor = Color.White
                ),
            ) {
                Text(text = stringResource(R.string.paste_link), style = Typography.labelMedium.plus(TextStyle(color = Color.White)))
            }
        }

        item {
            Column {
                Text(
                    modifier = Modifier.padding(top = 50.dp),
                    text = stringResource(R.string.when_you_re_ready).uppercase(),
                    style = Typography.bodyLarge.plus( TextStyle(fontWeight = FontWeight.Bold ))
                )

                Text(
                    text = stringResource(R.string.your_animal_will_be_saved_as_long_as_the_app_is_not_quit)
                )
            }


        }

        item {


            Button(onClick = {
                onSave(model)

                Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
            }, modifier = Modifier.padding(top = 20.dp, bottom = 100.dp),
                colors = ButtonDefaults.buttonColors(
                containerColor = Blue500,
                contentColor = Color.White
            ), enabled = listOf(
                    animalName,
                    animalDescription,
                    animalPhotoUrlAsString
                ).none { it == "" }
            ) {
                Text(text = stringResource(R.string.save, model.name), style = Typography.labelMedium.plus(TextStyle(color = Color.White)))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
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
                AddAnimal(thisAnimalId = (models.size + 1).toLong(), context = LocalContext.current,
                    backAction = { navController.navigate("singleItemScreen") },
                    onSave = { })
            }
        }
    }
}