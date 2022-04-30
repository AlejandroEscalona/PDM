package com.example.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mywallet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ActiveFragment : Fragment
    private lateinit var FragmentManager : FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}