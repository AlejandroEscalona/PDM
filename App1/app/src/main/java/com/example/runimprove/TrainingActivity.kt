package com.example.runimprove

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.runimprove.databinding.ActivityTrainingBinding
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate.now


class TrainingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainingBinding
    var porcentajeEntreno = 0.0
    private lateinit var database: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)

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

    fun onSaveClicked(view: View){
        var completado = ((porcentajeEntreno / 12.0)*100).toInt().toDouble()
        val id = database.insertEntreno("Sprints",completado,now().toString())
        if(id != -1L ){
            Snackbar.make(binding.root, "Guardado con exito.", Snackbar.LENGTH_SHORT).show()
        } else{
            Snackbar.make(binding.root, "Error al guardar.", Snackbar.LENGTH_SHORT).show()
        }
    }


    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.sprint_serie1 -> {
                    if (checked) {
                        Toast.makeText(this, "Muy bien, sigue así!!", Toast.LENGTH_SHORT).show()
                        porcentajeEntreno = porcentajeEntreno.plus(1)
                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }
                R.id.sprint_serie2 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }
                R.id.sprint_serie3 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }R.id.sprint_serie4 -> {
                    if (checked) {
                        Toast.makeText(this, "Vamos, a por el siguiente ejercicio",
                            Toast.LENGTH_SHORT).show()
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }com.example.runimprove.R.id.jumping_serie1 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }com.example.runimprove.R.id.jumping_serie2 -> {
                    if (checked) {
                        Toast.makeText(this, "Muy bien, sigue así!!", Toast.LENGTH_SHORT).show()
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }com.example.runimprove.R.id.jumping_serie3 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }com.example.runimprove.R.id.jumping_serie4 -> {
                    if (checked) {
                        Toast.makeText(this, "Muy bien, sigue así!!", Toast.LENGTH_SHORT).show()
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }com.example.runimprove.R.id.series_serie1 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }com.example.runimprove.R.id.series_serie2 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }com.example.runimprove.R.id.series_serie3 -> {
                    if (checked) {
                        Toast.makeText(this, "Vamos ya casi acabas", Toast.LENGTH_SHORT).show()
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }com.example.runimprove.R.id.series_serie4 -> {
                    if (checked) {
                        Toast.makeText(this, "Muy bien, has acabado!!", Toast.LENGTH_SHORT).show()
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }
            }
        }
    }



}