package com.example.mywallet

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywallet.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var database: DatabaseHelper
    private lateinit var movimientoAdapter: MovimientoAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)
        getMovimientos()
        binding.CantidadBalance.text = database.getBalance().toString()+"€"
        binding.recyclerViewMovimientos.setHasFixedSize(true)

        setupBottomNav()

//        Borrado con swipe
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                Toast.makeText(this@MainActivity, "on Move", Toast.LENGTH_SHORT).show()
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                val movimiento = movimientoAdapter.getIndex(position)
                val builder = AlertDialog.Builder(this@MainActivity)
                    .setTitle(getString(R.string.borrar_movimiento))
                    .setPositiveButton(getString(R.string.eliminar)) { dialogInterface, i ->
                        if (database.deleteMovimiento(movimiento)) {
                            movimientoAdapter.remove(movimiento)
                            movimientoAdapter.notifyDataSetChanged()
                            Snackbar.make(binding.root, getString(R.string.borrado_exito), Snackbar.LENGTH_SHORT).show()
                            binding.CantidadBalance.text = database.getBalance().toString()+"€"

                        }else
                            Snackbar.make(binding.root, getString(R.string.error_borrar), Snackbar.LENGTH_SHORT).show()
                    }
                    .setNegativeButton(getString(R.string.cancelar),null)
                builder.create().show()
                movimientoAdapter.notifyDataSetChanged()

            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewMovimientos)

    } // Fin onCreate

    @SuppressLint("SetTextI18n")
    override fun onRestart() {
        super.onRestart()
        getMovimientos()
        binding.CantidadBalance.text = database.getBalance().toString()+"€"
        binding.recyclerViewMovimientos.setHasFixedSize(true)
    }


    private fun setupBottomNav(){
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
               R.id.action_camera -> {
                    val intent = Intent(this,MainActivity::class.java)
                    launchIntent(intent)
                    true
                }R.id.action_movements -> {
                    val intent = Intent(this,MovimientoActivity::class.java)
                    launchIntent(intent)
                    true
                }
                else -> false
            }
        }
    }

    /**
     * @author Alejandro Escalona García
     * @param intent : Intent que se va a lanzar
     * Lanza una activity
     */
    private fun launchIntent(intent: Intent){
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        } else {
            Toast.makeText(this,"No se encontro una app compatible", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getMovimientos(){
        val data = database.getAllMovimientos()
        movimientoAdapter = MovimientoAdapter(data,this)
        binding.recyclerViewMovimientos.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movimientoAdapter
        }
    }

    override fun onLongClick(movimiento: Movimiento) {
        TODO("Not yet implemented")
    }

}