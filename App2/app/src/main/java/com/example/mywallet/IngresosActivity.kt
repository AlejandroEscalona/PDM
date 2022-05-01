package com.example.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mywallet.databinding.ActivityIngresosBinding

class IngresosActivity : AppCompatActivity() {

    private lateinit var database: DatabaseHelper
    private lateinit var binding: ActivityIngresosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresosBinding.inflate(layoutInflater)
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