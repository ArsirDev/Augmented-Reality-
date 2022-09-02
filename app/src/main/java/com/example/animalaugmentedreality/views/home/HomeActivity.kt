package com.example.animalaugmentedreality.views.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.animalaugmentedreality.R
import com.example.animalaugmentedreality.databinding.ActivityHomeBinding
import com.example.animalaugmentedreality.utils.ZoomOutPageTransformer
import com.example.animalaugmentedreality.views.about.AboutActivity
import com.example.animalaugmentedreality.views.jenis.JenisActivity
import com.example.animalaugmentedreality.views.panduan.PanduanActivity
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import java.util.*

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null

    private val binding get() = _binding!!

    private var viewPager: ViewPager? = null

    private lateinit var wormDotsIndicator: WormDotsIndicator

    private var homeAdapter: HomeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialView()
        initialAction()
    }

    private fun initialAction() {
        with(binding) {
            btnJenis.setOnClickListener {
                startActivity(Intent(this@HomeActivity, JenisActivity::class.java))
                finishAffinity()
            }
            btnPanduan.setOnClickListener {
                startActivity(Intent(this@HomeActivity, PanduanActivity::class.java))
                finishAffinity()
            }
            btnAbout.setOnClickListener {
                startActivity(Intent(this@HomeActivity, AboutActivity::class.java))
                finishAffinity()
            }
            btnKeluar.setOnClickListener {
                showDialog();
            }
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setPositiveButton("Keluar") { _, _ ->
                finish()
            }
            .setNegativeButton("Batal") { i, _ ->
                i.dismiss()
            }
            .setMessage(getString(R.string.exit_message))
            .show()
    }

    private fun initialView() {
        with(binding) {
            viewPager = carousel
            this@HomeActivity.wormDotsIndicator = wormDotsIndicator
            homeAdapter = HomeAdapter(layoutInflater)
            viewPager?.adapter = homeAdapter
            viewPager?.setPageTransformer(true, ZoomOutPageTransformer())
            wormDotsIndicator.setViewPager(viewPager!!)
            automaticaliSlide()
        }
    }

    private fun automaticaliSlide() {
        var currentPage = 0
        val count = viewPager?.adapter?.count!!
        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            if (currentPage == count) {
                currentPage = -1
            }
            viewPager?.setCurrentItem(currentPage++, true)
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 3000, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewPager = null
        homeAdapter = null
    }
}