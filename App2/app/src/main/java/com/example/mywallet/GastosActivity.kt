package com.example.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mywallet.databinding.ActivityGastosBinding

class GastosActivity : AppCompatActivity() {
    private lateinit var database: DatabaseHelper
    private lateinit var binding: ActivityGastosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGastosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }
}