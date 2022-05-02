package com.example.mywallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mywallet.databinding.ActivityMainBinding
import com.example.mywallet.databinding.ActivityMovimientoBinding

class MovimientoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovimientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNav()
    }


    /**
     * @author Alejandro Escalona García
     * @param view : view
     * Obtiene que activity se va a lanzar
     */
    fun OnClickButton(view: View){
        when(view.id){
            R.id.cardIngresos -> {
                val intent = Intent(this,GastosActivity::class.java)
                launchIntent(intent)
            }
            R.id.cardGastos -> {
                val intent = Intent(this,GastosActivity::class.java)
                launchIntent(intent)
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

    private fun setupBottomNav(){
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> {
                    val intent = Intent(this,MainActivity::class.java)
                    launchIntent(intent)
                    true
                }R.id.action_camera -> {
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
}