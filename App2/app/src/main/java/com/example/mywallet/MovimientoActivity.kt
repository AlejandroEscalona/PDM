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

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    /**
     * @author Alejandro Escalona García
     * @param view : view
     * Obtiene que activity se va a lanzar
     */
    fun OnClickButton(view: View){
        when(view.id){
            R.id.cardIngresos -> {
                val intent = Intent(this,IngresosActivity::class.java)
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


}