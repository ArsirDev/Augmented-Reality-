package com.example.animalaugmentedreality.views.jenis.activity.herbivora

import android.Manifest.permission.CAMERA
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.animalaugmentedreality.R
import com.example.animalaugmentedreality.databinding.ActivityHerbivoraBinding
import com.example.animalaugmentedreality.utils.Content.CATEGORY
import com.example.animalaugmentedreality.utils.Content.GAJAH
import com.example.animalaugmentedreality.utils.Content.HERBIVORA
import com.example.animalaugmentedreality.utils.Content.KAMBING
import com.example.animalaugmentedreality.utils.Content.KELINCI
import com.example.animalaugmentedreality.utils.Content.KUDA
import com.example.animalaugmentedreality.utils.Content.NAME
import com.example.animalaugmentedreality.utils.FullScreenHelper
import com.example.animalaugmentedreality.utils.SnackbarHelper
import com.example.animalaugmentedreality.utils.setOnClickListenerWithDebounce
import com.example.animalaugmentedreality.views.detail.DetailActivity
import com.example.animalaugmentedreality.views.jenis.JenisActivity
import com.google.ar.core.Anchor
import com.google.ar.core.ArCoreApk
import com.google.ar.core.AugmentedImage
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Pose
import com.google.ar.core.Session
import com.google.ar.core.TrackingState
import com.google.ar.core.exceptions.CameraNotAvailableException
import com.google.ar.core.exceptions.UnavailableApkTooOldException
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException
import com.google.ar.core.exceptions.UnavailableSdkTooOldException
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.ArSceneView
import com.google.ar.sceneform.Camera
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import java.io.IOException

class HerbivoraActivity : AppCompatActivity(), Scene.OnUpdateListener {

    private var _binding: ActivityHerbivoraBinding? = null

    private val binding get() = _binding as ActivityHerbivoraBinding

    private var shouldConfigureSession = false

    private var session: Session? = null

    private var shouldAddModel = true

    private var viewRenderable: ViewRenderable? = null

    private var installRequested: Boolean = false

    private val messageSnackbarHelper: SnackbarHelper = SnackbarHelper()

    private var herbivoraList : HashMap<String, Int> = HashMap()

    private var title: String = String()

    private var arConfig: Config? = null

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            setUpSession()
        }
    }

    private fun setUpSession() {
        if (session == null) {
            try {
                when(ArCoreApk.getInstance().requestInstall(this, !installRequested)) {
                    ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                        installRequested = true
                        return
                    }
                    ArCoreApk.InstallStatus.INSTALLED -> {
                    }
                }
                session = Session(this)
            } catch (e: UnavailableArcoreNotInstalledException) {
                e.printStackTrace()
            } catch (e: UnavailableApkTooOldException) {
                e.printStackTrace()
            } catch (e: UnavailableSdkTooOldException) {
                e.printStackTrace()
            } catch (e: UnavailableDeviceNotCompatibleException) {
                e.printStackTrace()
            }
            shouldConfigureSession = true
        }
        if (shouldConfigureSession) {
            configureSession()
            shouldConfigureSession = false
            binding.arView.setupSession(session)
        }
        try {
            session!!.resume()
            binding.arView.resume()
        } catch (e: CameraNotAvailableException) {
            e.printStackTrace()
            session = null
            return
        }
    }

    private fun configureSession() {
        val config = Config(session)
        if (!buildDatabase(config)) {
            Toast.makeText(this, "Error built-in database", Toast.LENGTH_SHORT).show()
            return
        }
        config.apply {
            lightEstimationMode =  Config.LightEstimationMode.ENVIRONMENTAL_HDR
            focusMode = Config.FocusMode.AUTO
            updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
            depthMode =
                if (session!!.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
                    Config.DepthMode.AUTOMATIC
                } else {
                    Config.DepthMode.DISABLED
                }
        }
        session!!.configure(config)
    }

    private fun buildDatabase(config: Config): Boolean {
        val augmentedImageDatabase: AugmentedImageDatabase
        return try {
            val inputStream = assets.open("makanan.imgdb")
            augmentedImageDatabase = AugmentedImageDatabase.deserialize(session!!, inputStream)
            config.augmentedImageDatabase = augmentedImageDatabase
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        FullScreenHelper.setFullScreenOnWindowFocusChanged(this, hasFocus)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHerbivoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissionLauncher.launch(CAMERA)

        binding.arView.scene.addOnUpdateListener(this)

        iniHerbivoraList()

        initView()
    }

    private fun initView() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
    }

    private fun Session.setupAutoFocus() {
        arConfig = Config(this)
        if (arConfig?.focusMode == Config.FocusMode.FIXED)
            arConfig?.focusMode = Config.FocusMode.AUTO
        arConfig?.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
        configure(arConfig)
        binding.arView.setupSession(this)
        Log.e(this@HerbivoraActivity.toString(),"The camera is current in focus mode : ${config.focusMode.name}")
    }

    private fun iniHerbivoraList() {
        herbivoraList[GAJAH] = R.raw.gajah
        herbivoraList[KAMBING] = R.raw.kambing
        herbivoraList[KELINCI] = R.raw.kelinci
        herbivoraList[KUDA] = R.raw.kuda

        title = herbivoraList.keys.random()
    }

    override fun onResume() {
        super.onResume()
        try {
            requestPermissionLauncher.launch(CAMERA)
            binding.arView.resume()
            session?.let {
                session = Session(this)
                it.setupAutoFocus()
            }
        } catch (e: CameraNotAvailableException) {
            e.printStackTrace()
        }
    }

    override fun onRestart() {
        super.onRestart()
        session?.let {
            it.pause()
            binding.arView.pause()
        }
    }

    override fun onPause() {
        super.onPause()
        if (session != null) {
            session!!.pause()
            binding.arView.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (session != null) {
            session!!.close()
            session = null
            binding.arView.destroy()
        }
    }

    override fun onUpdate(p0: FrameTime?) {
        with(binding) {
            val frame = arView.arFrame!!
            val updateAugmentedImage =
                frame.getUpdatedTrackables(AugmentedImage::class.java).iterator()
            for (augmentedImage in updateAugmentedImage) {
                when (augmentedImage.trackingState) {
                    TrackingState.PAUSED -> {
                        val text = String.format("Detected Image %d", augmentedImage.index)
                        messageSnackbarHelper.showMessage(this@HerbivoraActivity, text)
                        return@with
                    }
                    TrackingState.TRACKING -> {
                        if (augmentedImage.name.equals("tanaman.jpg") && shouldAddModel) {
                            placeObject(
                                arView,
                                augmentedImage.createAnchor(augmentedImage.centerPose),
                                herbivoraList[title] ?: 0
                            )
                            shouldAddModel = false
                            return@onUpdate
                        }
                    }
                    TrackingState.STOPPED -> {
                        updateAugmentedImage.remove()
                        return@with
                    }
                    else -> {
                        break
                    }
                }
            }
        }
    }

    private fun placeObject(
        arView: ArSceneView,
        createAnchor: Anchor?,
        data: Int
    ) {
        ModelRenderable.builder()
            .setSource(arView.context, data)
            .build()
            .thenAccept { modelRenderable ->
                addNodeToScene(arView, createAnchor, modelRenderable)
            }
            .exceptionally { throwable ->
                Toast.makeText(arView.context, "Error:" + throwable.message, Toast.LENGTH_LONG)
                    .show()
                null
            }
        setName(title)
    }

    private fun addNodeToScene(
        arView: ArSceneView,
        anchor: Anchor?,
        modelRenderable: ModelRenderable?
    ) {
        val anchorNode = AnchorNode(anchor)
        val pose = Pose.makeTranslation(0f, 0f, 0f)
        val node = Node().apply {
            renderable = modelRenderable
            setParent(anchorNode)
        }

        binding.btnDelete.setOnClickListenerWithDebounce {
            shouldAddModel = true
            title = herbivoraList.keys.random()
            anchorNode.removeChild(node)
            anchor!!.detach()
            return@setOnClickListenerWithDebounce
        }

        anchorNode.addChild(Node().apply {
            renderable = viewRenderable
            localPosition = Vector3(pose.tx(), this.localPosition.y + 0.5f, pose.tz())
            setParent(anchorNode)
        })
        arView.scene.addChild(anchorNode)
        arView.scene.setOnTouchListener { _, _ ->
            Toast.makeText(this, "Model tidak dapat dimuat", Toast.LENGTH_SHORT).show()
            val bundle = Bundle().apply {
                putString(CATEGORY, HERBIVORA)
                putString(NAME, title)
            }
            startActivity(Intent(this, DetailActivity::class.java).putExtras(bundle))
            finish()
            true
        }
    }

    private fun setName(name: String) {
        ViewRenderable.builder()
            .setView(this, R.layout.name_layout)
            .build()
            .thenAccept { viewRenderable: ViewRenderable ->
                this.viewRenderable = viewRenderable.apply {
                    (this.view as TextView).apply {
                        text = name
                    }
                }
            }
            .exceptionally { throwable: Throwable? ->
                Toast.makeText(this, "Model tidak dapat dimuat", Toast.LENGTH_SHORT)
                    .show()
                null
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, JenisActivity::class.java))
        finish()
    }
}