package nz.phan.augment.data

import io.github.sceneview.ar.node.PlacementMode
import nz.phan.augment.R
import nz.phan.augment.entity.ArPlacement
import nz.phan.augment.entity.Model
import nz.phan.augment.entity.ModelResource

val modelResources: List<ModelResource>
    get() = listOf(
        ModelResource(
            id = 1L,
            nameResId = R.string.tiger,
            categoryResId =  R.string.mammal,
            imageResId = R.drawable.tiger_thumbnail,

            arPlacement = ArPlacement(
                resId = R.raw.tiger,
                scaleUnits = 2.5f,
                placementMode = PlacementMode.BEST_AVAILABLE,
                applyPoseRotation = false
            ),

            descriptionResId = R.string.tiger_description,

            wikipediaUriResId = R.string.tiger_wikipedia_link
        ),


        ModelResource(
            id = 2L,
            nameResId = R.string.brown_bear,
            categoryResId = R.string.mammal,
            imageResId = R.drawable.brown_bear_thumbnail,

            arPlacement = ArPlacement(
                resId = R.raw.brown_bear,
                scaleUnits = 2.5f,
                placementMode = PlacementMode.BEST_AVAILABLE,
                applyPoseRotation = false
            ),

            descriptionResId = R.string.brown_bear_description,
            wikipediaUriResId = R.string.brown_bear_wikipedia_link,
        ),

        ModelResource(
            id = 3L,
            nameResId = R.string.rabbit,
            categoryResId = R.string.mammal,
            imageResId = R.drawable.rabbit_thumbnail,

            arPlacement = ArPlacement(
                resId = R.raw.rabbit,
                scaleUnits = 2.5f,
                placementMode = PlacementMode.BEST_AVAILABLE,
                applyPoseRotation = false
            ),

            descriptionResId = R.string.rabbit_description,

            wikipediaUriResId = R.string.rabbit_wikipedia_link
        ),

        ModelResource(
            id = 4L,
            nameResId = R.string.cat,
            categoryResId = R.string.mammal,
            imageResId = R.drawable.cat_thumbnail,

            arPlacement = ArPlacement(
                resId = R.raw.cat,
                scaleUnits = 2.5f,
                placementMode = PlacementMode.BEST_AVAILABLE,
                applyPoseRotation = false
            ),

            descriptionResId = R.string.cat_description,

            wikipediaUriResId = R.string.cat_wikipedia_link
        ),

        ModelResource(
            id = 5L,
            nameResId = R.string.eagle,
            categoryResId = R.string.bird,
            imageResId = R.drawable.eagle_thumbnail,

            arPlacement = ArPlacement(
                resId = R.raw.eagle,
                scaleUnits = 2.5f,
                placementMode = PlacementMode.BEST_AVAILABLE,
                applyPoseRotation = false
            ),

            descriptionResId = R.string.eagle_description,

            wikipediaUriResId = R.string.eagle_wikipedia_link
        ),
    )