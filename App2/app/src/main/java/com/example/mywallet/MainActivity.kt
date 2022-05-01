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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ActiveFragment : Fragment
    private lateinit var FragmentManager : FragmentManager
    private lateinit var database: DatabaseHelper
    private lateinit var movimientoAdapter: MovimientoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)
        setupBottomNav()
    }

    private fun setupBottomNav(){
        FragmentManager = supportFragmentManager

        val homeFragment = HomeFragment()
        val cameraFragment = CameraFragment()
        val movimientosFragment = MovimientosFragment()

        ActiveFragment = homeFragment

        FragmentManager.beginTransaction()
            .add(R.id.hostFragment,cameraFragment, CameraFragment::class.java.name)
            .hide(cameraFragment).commit()
        FragmentManager.beginTransaction()
            .add(R.id.hostFragment,movimientosFragment, MovimientosFragment::class.java.name)
            .hide(movimientosFragment).commit()
        FragmentManager.beginTransaction()
            .add(R.id.hostFragment,homeFragment, HomeFragment::class.java.name)
            .commit()

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> {
                    FragmentManager.beginTransaction().hide(ActiveFragment)
                        .show(homeFragment).commit()
                    ActiveFragment = homeFragment
                    true
                }R.id.action_camera -> {
                    FragmentManager.beginTransaction().hide(ActiveFragment)
                        .show(cameraFragment).commit()
                    ActiveFragment = cameraFragment
                    true
                }R.id.action_movements -> {
                    FragmentManager.beginTransaction().hide(ActiveFragment)
                        .show(movimientosFragment).commit()
                    ActiveFragment = movimientosFragment
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



}