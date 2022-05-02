package com.example.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
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

        val categorias = resources.getStringArray(R.array.gastosList)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, categorias)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }
}