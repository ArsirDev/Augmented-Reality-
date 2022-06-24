package com.example.animalaugmentedreality.views.splashscreen


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.animalaugmentedreality.R
import com.example.animalaugmentedreality.databinding.ActivitySplashScreenBinding
import com.example.animalaugmentedreality.utils.ScaleAnimationCustome
import com.example.animalaugmentedreality.utils.SessionManager
import com.example.animalaugmentedreality.views.home.HomeActivity
import com.example.animalaugmentedreality.views.welcome.WelcomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private var _binding: ActivitySplashScreenBinding? = null

    private val binding get() = _binding!!

    private val sessionManager: SessionManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        scale()
    }

    private fun scale() {

        val scale_show = ScaleAnimationCustome(
            0f,
            1f,
            0f,
            1f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 1000
            fillAfter = true
        }
        binding.ivLogo.startAnimation(scale_show)

        lifecycleScope.launch {
            delay(5000)
            if (sessionManager.getStatusFirstInstall()) {
                startActivity(Intent(applicationContext, WelcomeActivity ::class.java))
                finishAffinity()
            } else {
                startActivity(Intent(applicationContext, HomeActivity ::class.java))
                finishAffinity()
            }
        }
    }
}