//package nz.phan.augment.compose
//
//import android.content.Context
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.core.view.isGone
//import com.google.ar.core.Config
//import io.github.sceneview.ar.ARScene
//import io.github.sceneview.ar.getDescription
//import io.github.sceneview.ar.node.ArModelNode
//
//import io.github.sceneview.ar.node.ArNode
//import io.github.sceneview.ar.node.PlacementMode
//import io.github.sceneview.math.Position
//import nz.phan.augment.data.models
//import nz.phan.augment.entity.Model
//
//@Composable
//fun ARScreen(context: Context, model: Model, exitAction : () -> Unit) {
//    val nodes = remember { mutableStateListOf<ArNode>() }
//
//    var text by remember { mutableStateOf<String?>(null) }
//
//    if (model.arPlacement != null) {
//        Column {
//            text?.let { Text(text = it) }
//
//            Box(modifier = Modifier.fillMaxSize()) {
//                ARScene(
//                    modifier = Modifier.fillMaxSize(),
//                    nodes = nodes,
//                    planeRenderer = true,
//                    onCreate = {
//                        it.lightEstimationMode = Config.LightEstimationMode.ENVIRONMENTAL_HDR
//                        it.depthEnabled = true
//                        it.instantPlacementEnabled = true
//                        it.onArTrackingFailureChanged = { reason ->
//                            text = reason?.getDescription(context)
//                            //statusText.isGone = reason == null
//                        }
//                    },
//                    onSessionCreate = { session ->
//                        // Configure the ARCore session
//                    },
//                    onFrame = { arFrame ->
//                        // Retrieve ARCore frame update
//                    },
//                    onTap = { hitResult ->
//                        // User tapped in the AR view
//                    }
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ArPreview() {
//    ARScreen(models.first()) { }
//}