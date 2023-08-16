package nz.phan.augment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nz.phan.augment.compose.Catalogue
import nz.phan.augment.ui.theme.AugmentTheme


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false).apply {
            findViewById<ComposeView>(R.id.composeViewHome).setContent {
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

                    Catalogue(context = LocalContext.current)
                }
            }
        }
    }

}