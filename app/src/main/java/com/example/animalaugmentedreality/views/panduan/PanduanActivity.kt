package com.example.animalaugmentedreality.views.panduan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animalaugmentedreality.R
import com.example.animalaugmentedreality.databinding.ActivityPanduanBinding
import com.example.animalaugmentedreality.views.home.HomeActivity

class PanduanActivity : AppCompatActivity() {

    private var _binding: ActivityPanduanBinding? = null

    private val binding get() = _binding as ActivityPanduanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPanduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}