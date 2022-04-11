package com.example.runimprove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.runimprove.databinding.ActivityHistoricalBinding
import com.google.android.material.snackbar.Snackbar
import java.security.AccessController.getContext
import java.time.LocalTime.now

class HistoricalActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityHistoricalBinding
    private lateinit var database: DatabaseHelper
    private lateinit var entrenoAdapter: EntrenoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)
        getEntrenos()
        binding.recyclerViewEntrenos.setHasFixedSize(true)


        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        binding.btnDelete.setOnClickListener(){
                database.deleteAllEntreno()
                entrenoAdapter.removeAll()
                Snackbar.make(binding.root, getString(R.string.delete_all_entrenos), Snackbar.LENGTH_SHORT).show()
        }
        binding.btnGrafica.setOnClickListener(){
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

     fun getEntrenos(){
         val data = database.getAllEntrenos()
         entrenoAdapter = EntrenoAdapter(data,this)
            binding.recyclerViewEntrenos.apply {
                layoutManager = LinearLayoutManager(this@HistoricalActivity)
                adapter = entrenoAdapter
            }
    }


    override fun onLongClick(entreno: Entreno) {
        val builder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.borrar_entreno))
            .setPositiveButton(getString(R.string.eliminar)) { dialogInterface, i ->
                if (database.deleteEntreno(entreno)) {
                    entrenoAdapter.remove(entreno)
                    Snackbar.make(binding.root, getString(R.string.borrado_exito), Snackbar.LENGTH_SHORT).show()
                }else
                    Snackbar.make(binding.root, getString(R.string.error_borrar), Snackbar.LENGTH_SHORT).show()
            }
            .setNegativeButton(getString(R.string.cancelar),null)
        builder.create().show()
    }

}

