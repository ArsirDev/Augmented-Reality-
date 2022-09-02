package com.example.animalaugmentedreality.views.about

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animalaugmentedreality.R
import com.example.animalaugmentedreality.databinding.ActivityAboutBinding
import com.example.animalaugmentedreality.views.home.HomeActivity
import com.google.ar.core.Session

class AboutActivity : AppCompatActivity() {

    private var _binding: ActivityAboutBinding? = null

    private val binding get() = _binding as ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutBinding.inflate(layoutInflater)
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