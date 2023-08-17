package nz.phan.augment.entity

import io.github.sceneview.ar.node.PlacementMode

class ArPlacement(var resId: Int,
                  var scaleUnits: Float? = null,
                  var placementMode: PlacementMode = PlacementMode.BEST_AVAILABLE,
                  var applyPoseRotation: Boolean = true)