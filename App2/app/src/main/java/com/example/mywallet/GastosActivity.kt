package com.example.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.mywallet.databinding.ActivityGastosBinding
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

    fun onSaveClicked(view: View) {

        val inputCantidad = binding.etCantidad.text.toString().toDouble()
        val inputCategoria = binding.autoCompleteTextView.text.toString()

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        //Insertamos entreno en la base de datos
        //comprobando que al menos ha realizado algún ejercicio

        val id = database.insertMovimiento("Gasto",inputCategoria,
            inputCantidad,(LocalDate.now().format(formatter)).toString())
        if(id != -1L ){
                Snackbar.make(binding.root, getString(R.string.guardar_exito), Snackbar.LENGTH_SHORT).show()
            } else Snackbar.make(binding.root, getString(R.string.error_guardar), Snackbar.LENGTH_SHORT).show()

    }
}