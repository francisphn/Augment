package nz.phan.augment.data

import android.content.res.Resources
import io.github.sceneview.ar.node.PlacementMode
import nz.phan.augment.R
import nz.phan.augment.entity.ArPlacement
import nz.phan.augment.entity.Model

val models: List<Model>
    get() = listOf(
        Model(
            id = 1L,
            name = Resources.getSystem().getString(R.string.tiger),
            categoryName = Resources.getSystem().getString(R.string.panthera),
            imageId = R.drawable.tiger_thumbnail,

            arPlacement = ArPlacement(
                resId = R.raw.tiger,
                scaleUnits = 2.5f,
                placementMode = PlacementMode.BEST_AVAILABLE,
                applyPoseRotation = false
            ),

            description = Resources.getSystem().getString(R.string.tiger_description).trimMargin(),

            wikipediaUri = Resources.getSystem().getString(R.string.tiger_wikipedia_link)
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

