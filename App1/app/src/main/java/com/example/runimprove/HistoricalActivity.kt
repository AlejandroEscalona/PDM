package com.example.runimprove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.runimprove.databinding.ActivityHistoricalBinding

class HistoricalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricalBinding
    private lateinit var database: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)
        getEntrenos()
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    private fun getEntrenos(){
        val data = database.getAllEntrenos()
//        data.forEach { entreno ->
//
//        }
    }
}