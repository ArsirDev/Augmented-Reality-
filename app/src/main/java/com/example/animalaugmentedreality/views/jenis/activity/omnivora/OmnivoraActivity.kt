package com.example.animalaugmentedreality.views.jenis.activity.omnivora

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.animalaugmentedreality.R
import com.example.animalaugmentedreality.databinding.ActivityOmnivoraBinding
import com.example.animalaugmentedreality.utils.Content.AYAM
import com.example.animalaugmentedreality.utils.Content.BEBEK
import com.example.animalaugmentedreality.utils.Content.KUCING
import com.example.animalaugmentedreality.utils.Content.PAUS
import com.example.animalaugmentedreality.utils.SnackbarHelper
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
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import java.io.IOException

class OmnivoraActivity : AppCompatActivity(), Scene.OnUpdateListener {

    private var _binding: ActivityOmnivoraBinding? = null

    private val binding get() = _binding as ActivityOmnivoraBinding

    private var shouldConfigureSession = false

    private var session: Session? = null

    private var shouldAddModel = true

    private var viewRenderable: ViewRenderable? = null

    private var installRequest: Boolean = false

    private val messageSnackbarHelper: SnackbarHelper = SnackbarHelper()

    private var omnivoraList : HashMap<String, Int> = HashMap()

    private var title: String = String()

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            setUpSession()
        }
    }

    private fun setUpSession() {
        if (session == null) {
            try {
                when(ArCoreApk.getInstance().requestInstall(this, !installRequest)) {
                    ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                        installRequest = true
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
        if (!buildDatabase(config)){
            Toast.makeText(this, "Error built-in database", Toast.LENGTH_SHORT).show()
        } else {
            config.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
            session!!.configure(config)
        }
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOmnivoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissionLauncher.launch(Manifest.permission.CAMERA)

        binding.arView.scene.addOnUpdateListener(this)

        initList()

        initView()
    }

    private fun initView() {
        binding.btnDelete.setOnClickListener {
            startActivity(Intent(this, JenisActivity::class.java))
            finish()
        }
    }

    private fun initList() {
        omnivoraList[AYAM] = R.raw.ayam
        omnivoraList[BEBEK] = R.raw.bebek
        omnivoraList[KUCING] = R.raw.kucing
        omnivoraList[PAUS] = R.raw.paus

        title = omnivoraList.keys.random()
    }

    override fun onResume() {
        super.onResume()
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        binding.arView.resume()
    }

    override fun onRestart() {
        super.onRestart()
        if (session != null) {
            session!!.pause()
            binding.arView.pause()
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
                        messageSnackbarHelper.showMessage(this@OmnivoraActivity, text)
                        break
                    }
                    TrackingState.TRACKING -> {
                        if (augmentedImage.name.equals("daging.jpg") && shouldAddModel) {
                            placeObject(
                                arView,
                                augmentedImage.createAnchor(augmentedImage.centerPose),
                                omnivoraList[title] ?: 0
                            )
                            shouldAddModel = false
                            break
                        } else if (augmentedImage.name.equals("tanaman.jpg") && shouldAddModel) {
                            placeObject(
                                arView,
                                augmentedImage.createAnchor(augmentedImage.centerPose),
                                omnivoraList[title] ?: 0
                            )
                            shouldAddModel = false
                            break
                        }
                    }
                    TrackingState.STOPPED -> {
                        updateAugmentedImage.remove()
                        break
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
        Node().apply {
            renderable = modelRenderable
            setParent(anchorNode)
        }
        anchorNode.addChild(Node().apply {
            renderable = viewRenderable
            localPosition = Vector3(pose.tx(), this.localPosition.y + 0.5f, pose.tz())
            setParent(anchorNode)
        })
        arView.scene.apply {

        }
        arView.scene.addChild(anchorNode)
        arView.scene.setOnTouchListener { hitTestResult, motionEvent ->
            Toast.makeText(this, "Model tidak dapat dimuat", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DetailActivity::class.java).putExtra("nama", title))
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

    override fun onDestroy() {
        super.onDestroy()
        if (session != null) {
            session!!.close()
            session = null
            binding.arView.destroy()
        }
    }

    override fun onPause() {
        super.onPause()
        if (session != null) {
            session!!.pause()
            binding.arView.pause()
        }
    }
}