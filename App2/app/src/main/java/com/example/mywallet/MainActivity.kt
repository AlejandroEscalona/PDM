package com.example.mywallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywallet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var database: DatabaseHelper
    private lateinit var movimientoAdapter: MovimientoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)
        getMovimientos()
        setupBottomNav()
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