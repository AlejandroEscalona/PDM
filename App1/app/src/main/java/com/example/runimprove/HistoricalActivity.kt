package com.example.runimprove

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.runimprove.databinding.ActivityHistoricalBinding
import com.google.android.material.snackbar.Snackbar


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
            launchIntent()
        }


        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                Toast.makeText(this@HistoricalActivity, "on Move", Toast.LENGTH_SHORT).show()
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                val entreno = entrenoAdapter.getIndex(position)
                val builder = AlertDialog.Builder(this@HistoricalActivity)
                    .setTitle(getString(R.string.borrar_entreno))
                    .setPositiveButton(getString(R.string.eliminar)) { dialogInterface, i ->
                        if (database.deleteEntreno(entreno)) {
                            entrenoAdapter.remove(entreno)
                            entrenoAdapter.notifyDataSetChanged()
                            Snackbar.make(binding.root, getString(R.string.borrado_exito), Snackbar.LENGTH_SHORT).show()
                        }else
                            Snackbar.make(binding.root, getString(R.string.error_borrar), Snackbar.LENGTH_SHORT).show()
                    }
                    .setNegativeButton(getString(R.string.cancelar),null)
                builder.create().show()
                Toast.makeText(this@HistoricalActivity, getString(R.string.borrado_exito), Toast.LENGTH_SHORT).show()
                //Remove swiped item from list and notify the RecyclerView
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewEntrenos)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    /**
     * @author Alejandro Escalona García
     * Obtiene todos los entrenos de la base de datos
     */
     fun getEntrenos(){
         val data = database.getAllEntrenos()
         entrenoAdapter = EntrenoAdapter(data,this)
            binding.recyclerViewEntrenos.apply {
                layoutManager = LinearLayoutManager(this@HistoricalActivity)
                adapter = entrenoAdapter
            }
    }


    /**
     * @author Alejandro Escalona García
     * @param entreno : Entreno que se va a borrar
     * Borra un entreno al mantener pulsado sobre el mismo
     */
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

    @SuppressLint("QueryPermissionsNeeded")
    private fun launchIntent(){
        val intent = Intent(this,GraficaActivity::class.java)
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        } else {
            Toast.makeText(this,"No se encontro una app compatible", Toast.LENGTH_SHORT).show()
        }
    }




}

