package nz.phan.augment

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.google.ar.core.Config
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.getDescription
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position
import io.github.sceneview.utils.doOnApplyWindowInsets
import io.github.sceneview.utils.getResourceUri
import io.github.sceneview.utils.setFullScreen
import nz.phan.augment.data.models
import nz.phan.augment.entity.Model

class ArActivity : AppCompatActivity(R.layout.activity_ar) {

    private lateinit var sceneView: ArSceneView
    private lateinit var loadingView: View
    private lateinit var statusText: TextView
    private lateinit var placeModelButton: MaterialButton
    private lateinit var newModelButton: MaterialButton

    private lateinit var renderingModel: Model
    private var modelIndex = 0
    private var modelNode: ArModelNode? = null

    private var isLoading = false
        set(value) {
            field = value
            loadingView.isGone = !value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras: Bundle? = intent.extras;

        if (extras != null) {
            val modelId = extras.getLong("modelId")

            renderingModel = models.first { it.id == modelId }
        }

        setFullScreen(
            findViewById(R.id.rootView),
            fullScreen = true,
            hideSystemBars = false,
            fitsSystemWindows = false
        )

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar)?.apply {
            doOnApplyWindowInsets { systemBarsInsets ->
                (layoutParams as ViewGroup.MarginLayoutParams).topMargin = systemBarsInsets.top
            }
            title = ""
        })
        statusText = findViewById(R.id.statusText)
        sceneView = findViewById<ArSceneView?>(R.id.sceneView).apply {
            lightEstimationMode = Config.LightEstimationMode.ENVIRONMENTAL_HDR
            depthEnabled = true
            instantPlacementEnabled = true
            onArTrackingFailureChanged = { reason ->
                statusText.text = reason?.getDescription(context)
                statusText.isGone = reason == null
            }
        }
        loadingView = findViewById(R.id.loadingView)

        newModelButton = findViewById<MaterialButton>(R.id.newModelButton).apply {
            // Add system bar margins
            val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
            doOnApplyWindowInsets { systemBarsInsets ->
                (layoutParams   as ViewGroup.MarginLayoutParams).bottomMargin =
                    systemBarsInsets.bottom + bottomMargin
            }

            // Render button name
            this.text = context.getString(R.string.add_to_environment, renderingModel.name)

            setOnClickListener {
                it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                newModelNode()
                it.isVisible = false
            }
        }
        placeModelButton = findViewById<MaterialButton>(R.id.placeModelButton).apply {
            val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
            doOnApplyWindowInsets { systemBarsInsets ->
                (layoutParams   as ViewGroup.MarginLayoutParams).bottomMargin =
                    systemBarsInsets.bottom + bottomMargin
            }

            this.text = context.getString(R.string.fixate_in_one_place, renderingModel.name)

            setOnClickListener {
                it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                placeModelNode()
                it.isVisible = false
            }
        }

        placeModelButton.isVisible = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_ar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.isChecked = !item.isChecked
        modelNode?.detachAnchor()
        modelNode?.placementMode = when (item.itemId) {
            R.id.menuPlanePlacement -> PlacementMode.PLANE_HORIZONTAL_AND_VERTICAL
            R.id.menuInstantPlacement -> PlacementMode.INSTANT
            R.id.menuDepthPlacement -> PlacementMode.DEPTH
            R.id.menuBestPlacement -> PlacementMode.BEST_AVAILABLE
            else -> PlacementMode.DISABLED
        }
        return super.onOptionsItemSelected(item)
    }

    private fun placeModelNode() {
        modelNode?.anchor()
        placeModelButton.isVisible = false
        sceneView.planeRenderer.isVisible = false
    }

    private fun newModelNode() {
        isLoading = true

        modelNode?.takeIf { !it.isAnchored }?.let {
            sceneView.removeChild(it)
            it.destroy()
        }

        val renderingInfo = renderingModel.arPlacement

        val glbFileLocation = getResourceUri(renderingInfo!!.resId)

        modelIndex = (modelIndex + 1) % models.size

        modelNode = ArModelNode(sceneView.engine, renderingInfo.placementMode).apply {
            isSmoothPoseEnable = true
            applyPoseRotation = renderingInfo.applyPoseRotation
            loadModelGlbAsync(
                glbFileLocation = glbFileLocation,
                autoAnimate = true,
                scaleToUnits = renderingInfo.scaleUnits,
                // Place the model origin at the bottom center
                centerOrigin = Position(y = -1.0f)
            ) {
                sceneView.planeRenderer.isVisible = true
                isLoading = false
                placeModelButton.isVisible = true
            }
            onAnchorChanged = { anchor ->
                placeModelButton.isGone = anchor != null
            }
            onHitResult = { node, _ ->
                placeModelButton.isGone = !node.isTracking
            }
        }
        sceneView.addChild(modelNode!!)
        // Select the model node by default (the model node is also selected on tap)
        sceneView.selectedNode = modelNode
    }
}