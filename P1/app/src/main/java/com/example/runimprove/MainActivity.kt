package com.example.runimprove

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.runimprove.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        OnClickButton(binding.root)*/
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    fun OnClickButton(view: View){

        when(view.id){
            R.id.btnRoutines -> {
                val intent = Intent(this,RoutinesActivity::class.java)
                launchIntent(intent)
            }
            R.id.btnDiary -> {
                val intent = Intent(this,DiaryActivity::class.java)
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