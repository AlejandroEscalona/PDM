package com.example.runimprove

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

//    private fun launchFragment(fragment: Fragment, bundle: Bundle) {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.add(binding.containerMain.id,fragment)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()
//    }


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
        //val data = database.stadistic()

        val intent = Intent(this,GraficaActivity::class.java)

        intent.putExtra("prueba",1993)
//        intent.putExtra(getString(R.string.sprints),1)
//        intent.putExtra(getString(R.string.hit),2)
//        intent.putExtra(getString(R.string.bajadas),3)
//        intent.putExtra(getString(R.string.resistencia),4)
//        intent.putExtra(getString(R.string.tecnica),5)
//        intent.putExtra(getString(R.string.estiramientos),6)


        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        } else {
            Toast.makeText(this,"No se encontro una app compatible", Toast.LENGTH_SHORT).show()
        }
    }

}

