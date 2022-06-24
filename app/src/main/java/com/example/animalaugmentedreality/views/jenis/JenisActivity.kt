package com.example.animalaugmentedreality.views.jenis

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.animalaugmentedreality.databinding.ActivityJenisBinding
import com.example.animalaugmentedreality.utils.transformer
import com.example.animalaugmentedreality.views.home.HomeActivity
import com.example.animalaugmentedreality.views.jenis.activity.karnivora.KarnivoraActivity
import com.example.animalaugmentedreality.views.jenis.activity.omnivora.OmnivoraActivity
import com.example.animalaugmentedreality.views.jenis.activity.herbivora.HerbivoraActivity
import com.example.animalaugmentedreality.views.jenis.activity.insektivora.InsektivoraActivity
import com.example.animalaugmentedreality.views.jenis.adapter.JenisAdapter
import kotlinx.android.synthetic.main.activity_jenis.*

class JenisActivity : AppCompatActivity() {

    private var _binding: ActivityJenisBinding? = null

    private val binding get() = _binding!!

    private var jenisAdapter: JenisAdapter? = null

    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityJenisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialViewPager()
    }

    private fun initialViewPager() {
        jenisAdapter = JenisAdapter()

        viewPager = vpJenis.apply {

            adapter = jenisAdapter

            offscreenPageLimit = 3

            clipToPadding = false

            pageMargin = 5

            setPageTransformer(true, transformer())

        }

        jenisAdapter?.setOnItemClickListener { position ->
            when (position) {
                0 -> {
                    startActivity(Intent(this, KarnivoraActivity::class.java))
                    finish()
                }
                1 -> {
                    startActivity(Intent(this, HerbivoraActivity::class.java))
                    finish()
                }
                2 -> {
                    startActivity(Intent(this, OmnivoraActivity::class.java))
                    finish()
                }
                3 -> {
                    startActivity(Intent(this, InsektivoraActivity::class.java))
                    finish()
                }
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, HomeActivity::class.java))
        finishAffinity()
        super.onBackPressed()
    }
}