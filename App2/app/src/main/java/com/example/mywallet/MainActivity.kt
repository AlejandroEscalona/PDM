package com.example.mywallet

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywallet.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.Executor
import androidx.biometric.BiometricPrompt;

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private lateinit var database: DatabaseHelper
    private lateinit var movimientoAdapter: MovimientoAdapter

    @SuppressLint("SetTextI18n", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)
        getMovimientos()
        binding.CantidadBalance.text = database.getBalance().toString()+"€"
        binding.recyclerViewMovimientos.setHasFixedSize(true)

        val tipos = resources.getStringArray(R.array.tipoList)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, tipos)
        binding.autoCompleteTextView1.setAdapter(arrayAdapter)

        val dates = resources.getStringArray(R.array.DateList)
        val arrayAdapter1 = ArrayAdapter(this, R.layout.dropdown_item, dates)
        binding.autoCompleteTextView2.setAdapter(arrayAdapter1)

        setupBottomNav()

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    findViewById<Button>(R.id.lock).visibility = View.GONE
                    Toast.makeText(applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        val biometricLoginButton =
            findViewById<Button>(R.id.lock)
        biometricLoginButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }


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
                    val intent = Intent(this,CameraActivity::class.java)
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
        val data = database.getAllMovimientos("ASC")
        movimientoAdapter = MovimientoAdapter(data,this)
        binding.recyclerViewMovimientos.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movimientoAdapter
        }
    }

    fun onFilter(view: View){
        val tipo = binding.autoCompleteTextView1.text.toString()
        val date = binding.autoCompleteTextView2.text.toString()

        movimientoAdapter = when(tipo){
            "Ingresos" -> {
                val data = database.getAllIngresos(date)
                MovimientoAdapter(data,this)
            }
            "Gastos" -> {
                val data = database.getAllGastos(date)
                MovimientoAdapter(data,this)
            }
            else -> {
                val data = database.getAllMovimientos(date)
                MovimientoAdapter(data,this)
            }
        }
        binding.recyclerViewMovimientos.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movimientoAdapter
        }
    }

    override fun onLongClick(movimiento: Movimiento) {
        TODO("Not yet implemented")
    }

}