package nz.phan.augment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nz.phan.augment.compose.screen.Catalogue
import nz.phan.augment.compose.screen.SingleItem
import nz.phan.augment.data.models

import nz.phan.augment.entity.Settings
import nz.phan.augment.ui.theme.AugmentTheme


class HomeFragment : Fragment() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false).apply {
            findViewById<ComposeView>(R.id.composeViewHome).setContent {
                AugmentTheme {
                    val navController = rememberNavController()

                    val systemUiController = rememberSystemUiController()

                    val settings by remember { mutableStateOf(Settings("en-NZ")) }

                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = Color.Black
                        )

                        systemUiController.setNavigationBarColor(
                            color = Color.White,
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
                            itemAction = { navController.navigate("singleItemScreen/${it.id}") }, models = models.asList())
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
                                    model = models.first { m -> m.id == it.toLong() },
                                    backAction = { navController.popBackStack() },
                                    suggestedCharacterAction = { navController.navigate("singleItemScreen/${it.id}") },
                                    models = models.asList())
                            }
                        }
                    }
                }
            }
        }
    }

}