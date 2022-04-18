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
import java.time.format.DateTimeFormatter


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
            porcentajeEntreno=0.0
            launchFragment(fragment)
        }
        binding.cardBajadas.setOnClickListener {
            val fragment = BajadasFragment()
            porcentajeEntreno=0.0
            launchFragment(fragment)
        }
        binding.cardHit.setOnClickListener {
            val fragment = HitFragment()
            porcentajeEntreno=0.0
            launchFragment(fragment)
        }
        binding.cardResistencia.setOnClickListener {
            val fragment = ResistenciaFragment()
            porcentajeEntreno=0.0
            launchFragment(fragment)
        }
        binding.cardTecnica.setOnClickListener {
            val fragment = TecnicaFragment()
            porcentajeEntreno=0.0
            launchFragment(fragment)
        }
        binding.cardEstiramientos.setOnClickListener {
            val fragment = EstiramientosFragment()
            porcentajeEntreno=0.0
            launchFragment(fragment)
        }
    }

    private fun launchFragment(fragment: Fragment) {
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
        var tipo = ""
        //Obtenemos el tipo de entreno
        when(view.id){
            R.id.btnSaveSprint -> tipo = getString(R.string.sprints)
            R.id.btnSaveTecnica -> tipo = getString(R.string.tecnica)
            R.id.btnSaveEstiramientos -> tipo = getString(R.string.estiramientos)
            R.id.btnSaveBajadas -> tipo = getString(R.string.bajadas)
            R.id.btnSaveResistencia -> tipo = getString(R.string.resistencia)
            R.id.btnSaveHit -> tipo = getString(R.string.hit)
        }

        val completado = ((porcentajeEntreno / 12.0)*100).toInt().toDouble()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        //Insertamos entreno en la base de datos
        if(completado > 0){
            val id = database.insertEntreno(tipo,completado,(now().format(formatter)).toString())
            if(id != -1L ){
                Snackbar.make(binding.root, getString(R.string.guardar_exito), Snackbar.LENGTH_SHORT).show()
            } else Snackbar.make(binding.root, getString(R.string.error_guardar), Snackbar.LENGTH_SHORT).show()
        }else Snackbar.make(binding.root, getString(R.string.marcar_serie), Snackbar.LENGTH_SHORT).show()
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.entrenamiento1_serie1 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)
                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }
                R.id.entrenamiento1_serie2 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }
                R.id.entrenamiento1_serie3 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }R.id.entrenamiento1_serie4 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }R.id.calentamiento_serie1 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }R.id.calentamiento_serie2 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }R.id.calentamiento_serie3 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }R.id.calentamiento_serie4 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }R.id.entrenamiento2_serie1 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }R.id.entrenamiento2_serie2 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }R.id.entrenamiento2_serie3 -> {
                    if (checked) {
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }R.id.entrenamiento2_serie4 -> {
                    if (checked) {
                        Toast.makeText(this, getString(R.string.acabado), Toast.LENGTH_SHORT).show()
                        porcentajeEntreno = porcentajeEntreno.plus(1)

                    } else {
                        porcentajeEntreno = porcentajeEntreno.minus(1)
                    }
                }
            }
        }
    }
}

