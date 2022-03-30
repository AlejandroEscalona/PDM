package com.example.runimprove

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.runimprove.databinding.ActivityTrainingBinding



class TrainingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        binding.cardSprint.setOnClickListener {
            val fragment = SprintsFragment()
            launchFragment(fragment)
        }
        binding.cardBajadas.setOnClickListener {
            val fragment = BajadasFragment()
            launchFragment(fragment)
        }
        binding.cardHit.setOnClickListener {
            val fragment = HitFragment()
            launchFragment(fragment)
        }
        binding.cardResistencia.setOnClickListener {
            val fragment = ResistenciaFragment()
            launchFragment(fragment)
        }
        binding.cardTecnica.setOnClickListener {
            val fragment = TecnicaFragment()
            launchFragment(fragment)
        }
        binding.cardEstiramientos.setOnClickListener {
            val fragment = EstiramientosFragment()
            launchFragment(fragment)
        }

    }

    private fun launchFragment(fragment: Fragment) {
        //val fragment = SprintsFragment()

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(binding.containerMain.id,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }


}