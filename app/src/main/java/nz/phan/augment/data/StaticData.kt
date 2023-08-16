package nz.phan.augment.data

import io.github.sceneview.ar.node.PlacementMode
import nz.phan.augment.R
import nz.phan.augment.entity.ArPlacement
import nz.phan.augment.entity.Model

val models = arrayOf(
    Model(
        id = 1L,
        name = "Tiger",
        categoryName =  "Panthera",
        imageId = R.drawable.tiger_thumbnail,

        arPlacement = ArPlacement(
            resId = R.raw.tiger,
            scaleUnits = 2.5f,
            placementMode = PlacementMode.BEST_AVAILABLE,
            applyPoseRotation = false
        ),

        description = "The tiger (Panthera tigris) is the largest living cat species and a " +
                "member of the genus Panthera. " +
                "It is most recognisable for its dark vertical stripes on orange fur with a " +
                "white underside. " +
                "An apex predator, it primarily preys on ungulates, such as deer and wild boar. " +
                "It is territorial and generally a solitary but social predator, " +
                "requiring large contiguous areas of habitat to support its requirements for prey " +
                "and rearing of its offspring. " +
                "Tiger cubs stay with their mother for about two years and then become " +
                "independent, " +
                "leaving their mother's home range to establish their own."
    ),


    Model(
        id = 2L,
        name = "Brown Bear",
        categoryName =  "Panthera",
        imageId = R.drawable.tiger_thumbnail,

        arPlacement = ArPlacement(
            resId = R.raw.brown_bear,
            scaleUnits = 2.5f,
            placementMode = PlacementMode.BEST_AVAILABLE,
            applyPoseRotation = false
        ),

        description = "The tiger (Panthera tigris) is the largest living cat species and a " +
                "member of the genus Panthera. " +
                "It is most recognisable for its dark vertical stripes on orange fur with a " +
                "white underside. " +
                "An apex predator, it primarily preys on ungulates, such as deer and wild boar. " +
                "It is territorial and generally a solitary but social predator, " +
                "requiring large contiguous areas of habitat to support its requirements for prey " +
                "and rearing of its offspring. " +
                "Tiger cubs stay with their mother for about two years and then become " +
                "independent, " +
                "leaving their mother's home range to establish their own."
    ),
)