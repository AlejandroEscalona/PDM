package com.example.runimprove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.runimprove.databinding.ActivityHistoricalBinding
import com.google.android.material.snackbar.Snackbar
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


        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
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
                    Snackbar.make(binding.root, "Borrado con exito.", Snackbar.LENGTH_SHORT).show()
                }else
                    Snackbar.make(binding.root, "Error al borrar", Snackbar.LENGTH_SHORT).show()
            }
            .setNegativeButton(getString(R.string.cancelar),null)
        builder.create().show()
    }


}

