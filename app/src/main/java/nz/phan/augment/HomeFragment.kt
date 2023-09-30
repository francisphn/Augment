package nz.phan.augment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nz.phan.augment.compose.screen.Catalogue
import nz.phan.augment.compose.screen.AddAnimal
import nz.phan.augment.compose.screen.SingleItem
import nz.phan.augment.data.modelResources
import nz.phan.augment.entity.Model
import nz.phan.augment.entity.toModel

import nz.phan.augment.ui.theme.AugmentTheme
import nz.phan.augment.ui.theme.Translucent


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false).apply {
            findViewById<ComposeView>(R.id.composeViewHome).setContent {
                AugmentTheme {
                    val models = modelResources.map { it -> it.toModel(action = { r -> stringResource(id = r) } ) }

                    val navController = rememberNavController()

                    val systemUiController = rememberSystemUiController()

                    var customModels by remember { mutableStateOf(listOf<Model>()) }

                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = Translucent,
                            darkIcons = true,
                        )

                        systemUiController.setNavigationBarColor(
                            color = Color.Transparent,
                            darkIcons = true
                        )
                    }

                    NavHost(navController, startDestination = "catalogueScreen") {
                        composable("catalogueScreen",
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Up,
                                    animationSpec = tween(500)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Down  ,
                                    animationSpec = tween(500)
                                )
                            }) { Catalogue(LocalContext.current,
                            itemAction = { navController.navigate("singleItemScreen/${it.id}") }, models = (models + customModels).toList(),
                                settingsAction = { navController.navigate("settingsScreen") })
                        }


                        composable("singleItemScreen/{characterId}",
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Up,
                                    animationSpec = tween(500)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Down,
                                    animationSpec = tween(500)
                                )
                            }) { navBackStackEntry ->
                            val modelId = navBackStackEntry.arguments?.getString("characterId")
                            
                            modelId?.let { 
                                SingleItem(context = LocalContext.current,
                                    model = (models + customModels).toList().first { m -> m.id == it.toLong() },
                                    backAction = { navController.popBackStack() },
                                    suggestedCharacterAction = { navController.navigate("singleItemScreen/${it.id}") },
                                    models = (models + customModels).toList())
                                }
                        }

                        composable("settingsScreen",
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Up,
                                    animationSpec = tween(500)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Down  ,
                                    animationSpec = tween(500)
                                )
                            }) { AddAnimal(
                            thisAnimalId = ((modelResources + customModels).toList().size + 1).toLong(),
                            context = LocalContext.current,
                            backAction = { navController.popBackStack() }
                        ) {
                            val mutableCustomModels = customModels.toMutableList()
                            mutableCustomModels.add(it)
                            customModels = mutableCustomModels.toList()
                            navController.popBackStack()
                        }
                        }
                    }
                }
            }
        }
    }

}