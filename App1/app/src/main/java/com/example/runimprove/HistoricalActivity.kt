package com.example.runimprove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.runimprove.databinding.ActivityHistoricalBinding

class HistoricalActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityHistoricalBinding
    private lateinit var database: DatabaseHelper
    private lateinit var entrenoAdapter: EntrenoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)
//        var entrenos = getEntrenos()
        val entrenos = mutableListOf(
            Entreno(1,"Sprints",56.2),
            Entreno(2,"Resistencia",23.5),
            Entreno(2,"Técnica",87.5)
        )
        entrenoAdapter = EntrenoAdapter(entrenos,this)
        binding.recyclerViewEntrenos.apply {
            layoutManager = LinearLayoutManager(this@HistoricalActivity)
            adapter = entrenoAdapter
        }

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

    override fun onLongClick(entreno: Entreno) {
        TODO("Not yet implemented")
    }
}