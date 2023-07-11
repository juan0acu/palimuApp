package com.example.palimuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.palimuapp.databinding.ActivityHomeBinding
import com.example.palimuapp.databinding.ActivityLoginImboxBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {
        binding.btnAtras.setOnClickListener {
            finish()
        }
    }

}