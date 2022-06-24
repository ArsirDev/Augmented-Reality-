package com.example.animalaugmentedreality.views.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.example.animalaugmentedreality.R
import com.example.animalaugmentedreality.databinding.ActivityWelcomeBinding
import com.example.animalaugmentedreality.utils.SessionManager
import com.example.animalaugmentedreality.utils.removeView
import com.example.animalaugmentedreality.utils.showView
import com.example.animalaugmentedreality.views.home.HomeActivity
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class WelcomeActivity : AppCompatActivity() {

    private var _binding: ActivityWelcomeBinding? = null

    private val binding get() = _binding!!

    private lateinit var wormDotsIndicator: WormDotsIndicator

    private var welcomeAdapter: WelcomeAdapter? = null

    private val sessionManager: SessionManager by inject()

    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        initialViewPager()
        initialButton()
    }

    private fun initialButton() {
        with(binding){
            btnNext.setOnClickListener {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            }
        }
    }

    private fun initialViewPager() {
        viewPager = binding.vpWelcome
        wormDotsIndicator = binding.wormDotsIndicator
        welcomeAdapter = WelcomeAdapter(layoutInflater)
        viewPager?.adapter = welcomeAdapter
        wormDotsIndicator.setViewPager(viewPager!!)
        viewPager?.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    TransitionManager.beginDelayedTransition(binding.container)
                    if (binding.btnNext.visibility == View.VISIBLE){
                        binding.btnNext.showView()
                    }
                    binding.tvDescription.text = getString(R.string.welcome_description_one)
                } else if (position == 1) {
                    lifecycleScope.launch {
                        sessionManager.setStatusFirstInstall(false)
                        TransitionManager.beginDelayedTransition(binding.container)
                        if (binding.btnNext.visibility == View.GONE){
                            binding.btnNext.showView()
                        }
                        binding.tvDescription.text = getString(R.string.welcome_description_two)
                    }
                }
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}