package nz.phan.augment.entity

import io.github.sceneview.ar.node.PlacementMode
import nz.phan.augment.R

class Model(val id: Long, val name: String,
            val categoryName: String,
            val imageId: Int,
            val description: String,
            val arPlacement: ArPlacement = ArPlacement(
                resId = R.raw.tiger,
                scaleUnits = 2.5f,
                placementMode = PlacementMode.BEST_AVAILABLE,
                applyPoseRotation = false
            ))

