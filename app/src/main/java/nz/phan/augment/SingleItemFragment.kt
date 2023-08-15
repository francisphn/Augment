package nz.phan.augment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import nz.phan.augment.compose.Catalogue
import nz.phan.augment.ui.theme.AugmentTheme


class SingleItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_item, container, false).apply {
            findViewById<ComposeView>(R.id.composeView).setContent {
                AugmentTheme {
                    Catalogue(context = LocalContext.current)
                }
            }
        }
    }

}