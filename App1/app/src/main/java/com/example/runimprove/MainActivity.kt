package com.example.runimprove

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.runimprove.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)
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
            R.id.cardTraining -> {
                val intent = Intent(this,TrainingActivity::class.java)
                launchIntent(intent)
            }
            R.id.cardHistorial -> {
                val intent = Intent(this,HistoricalActivity::class.java)
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