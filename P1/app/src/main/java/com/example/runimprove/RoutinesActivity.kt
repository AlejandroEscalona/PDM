package com.example.runimprove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.RunImprove.databinding.ActivityRoutinesBinding

class RoutinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }
}



