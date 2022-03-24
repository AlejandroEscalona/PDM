package com.example.runimprove

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.runimprove.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    fun OnClickButton(view: View){

        when(view.id){
            R.id.btnTraining -> {
                val intent = Intent(this,TrainingActivity::class.java)
                launchIntent(intent)
            }
            R.id.btnHistorical -> {
                val intent = Intent(this,HistoricalActivity::class.java)
                launchIntent(intent)
            }
        }
    }

    private fun launchIntent(intent: Intent){
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        } else {
            Toast.makeText(this,"No se encontro una app compatible", Toast.LENGTH_SHORT)
        }
    }

}