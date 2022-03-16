package com.example.runimprove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.runimprove.databinding.ActivityHistoricalBinding

class HistoricalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoricalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }
}