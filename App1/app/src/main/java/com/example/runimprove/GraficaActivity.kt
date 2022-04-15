package com.example.runimprove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.runimprove.databinding.ActivityGraficaBinding

class GraficaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGraficaBinding
    private lateinit var database: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraficaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = DatabaseHelper(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        val datos = database.stadistic()
        binding.testIntent.setText(datos.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }
}