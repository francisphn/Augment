package nz.phan.augment.entity

import io.github.sceneview.ar.node.PlacementMode

class ArPlacement(val resId: Int,
                  val scaleUnits: Float? = null,
                  val placementMode: PlacementMode = PlacementMode.BEST_AVAILABLE,
                  val applyPoseRotation: Boolean = true)