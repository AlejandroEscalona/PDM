package com.example.RunImprove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.RunImprove.databinding.ActivityHistoricalBinding

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