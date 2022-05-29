package com.example.vit_dapp.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.vit_dapp.R
import com.example.vit_dapp.databinding.ActivityMainBinding
import com.example.vit_dapp.mainModule.viewModel.MainViewModel
import java.util.*
import com.example.vit_dapp.BR
import com.example.vit_dapp.common.utils.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()

    }

    private fun setupObservers() {
    binding.viewModel?.let {
            it.getSnackbarMessage().observe(this){resMsg ->
                Snackbar.make(binding.root,resMsg, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupViewModel() {
    val vm: MainViewModel by viewModels()
    binding.lifecycleOwner = this
    binding.setVariable(BR.viewModel, vm)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            binding.viewModel?.getWeatherAndForecast(37.18817, -3.60383,
                Constants.API_KEY,"metric","en")
        }
    }

}





