package com.example.animalaugmentedreality.views.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animalaugmentedreality.databinding.ActivityDetailBinding
import com.example.animalaugmentedreality.utils.AnimalDataState
import com.example.animalaugmentedreality.utils.Content.CATEGORY
import com.example.animalaugmentedreality.utils.Content.NAME
import com.example.animalaugmentedreality.views.jenis.JenisActivity

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initIntent()
        initView()
    }

    private fun initView() {
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, JenisActivity::class.java))
            finishAffinity()
        }
    }

    private fun initIntent() {
        intent.getStringExtra(CATEGORY)?.let { category ->
            intent.getStringExtra(NAME)?.let { name ->
                iniViewData(name, category)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun iniViewData(name: String, category: String) {
        AnimalDataState().getListData(category).map { detailItem ->
            binding.tvDetailTitle.text = "Detail $name"
            when (detailItem.title) {
                name -> {
                    with(binding) {
                        tvJudul.text = detailItem.title
                        tvDescription.text = detailItem.description
                        tvHabitat.text = detailItem.habitat
                        tvMakanan.text = detailItem.feed
                        tvJenis.text = detailItem.jenis
                    }
                }
                else -> {}
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, JenisActivity::class.java))
        finishAffinity()
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}