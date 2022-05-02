package com.example.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mywallet.databinding.ActivityCameraBinding

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false

    }
}