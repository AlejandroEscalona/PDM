package com.example.vit_dapp.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vit_dapp.R
import com.example.vit_dapp.databinding.ActivityMainBinding
import com.example.vit_dapp.mainModule.viewModel.MainViewModel
import java.util.*
import com.example.vit_dapp.BR
import com.example.vit_dapp.common.entities.Forecast
import com.example.vit_dapp.common.utils.CommonUtils
import com.example.vit_dapp.common.utils.Constants
import com.example.vit_dapp.mainModule.view.adapter.ForecastAdapter
import com.example.vit_dapp.mainModule.view.adapter.OnClickListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()
        setupAdapter()
        setupRecyclerView()

    }





    private fun setupObservers() {
    binding.viewModel?.let {
            it.getSnackbarMessage().observe(this){resMsg ->
                Snackbar.make(binding.root,resMsg, Snackbar.LENGTH_LONG).show()
            }
            it.getResult().observe(this){ result ->
                adapter.submitList(result.hourly)
            }
        }
    }

    private fun setupViewModel() {
    val vm: MainViewModel by viewModels()
    binding.lifecycleOwner = this
    binding.setVariable(BR.viewModel, vm)
    }

    private fun setupAdapter() {
        adapter = ForecastAdapter(this)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }


    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            binding.viewModel?.getWeatherAndForecast(37.18817, -3.60383,
                Constants.API_KEY,"metric","en")
        }
    }


    override fun onClick(forecast: Forecast) {
        Snackbar.make(binding.root,CommonUtils.getFullDate(forecast.dt), Snackbar.LENGTH_LONG).show()
    }

}





