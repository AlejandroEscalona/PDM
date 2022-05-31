package com.example.vit_dapp.mainModule.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.system.Os.remove
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
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
import java.io.IOException
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ForecastAdapter
    val chanelID = "vit_dapp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val saveRequest = PeriodicWorkRequestBuilder<DownloadWorker>(6, TimeUnit.HOURS)
                .build()
        WorkManager.getInstance(this).enqueue(saveRequest)




        setupViewModel()
        setupObservers()
        setupAdapter()
        setupRecyclerView()

    }

    private fun showNotification(Titulo: String, Mensaje: String) {
        //create notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = "VIT DAPP"
            val descriptionText = "VIT DAPP"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(chanelID, name, importance).apply {
                enableLights(true)
                LightingColorFilter(Color.RED, Color.RED)
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }


        //configure notification
        val notification = NotificationCompat.Builder(this, chanelID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("$Titulo")
            .setContentText("$Mensaje")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val notificationManager1 = NotificationManagerCompat.from(this)
        notificationManager1.notify(1, notification)
    }



    fun setupObservers() {
    binding.viewModel?.let {
            it.getSnackbarMessage().observe(this){resMsg ->
                Snackbar.make(binding.root,resMsg, Snackbar.LENGTH_LONG).show()
            }
            it.getResult().observe(this){ result ->
                adapter.submitList(result.hourly.drop(1).dropLast(28))
                checkUv(result.hourly.toMutableList())
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

    fun checkUv(list: MutableList<Forecast>){
        for (i in list.dropLast(28)){
            if (i.uvi > 4.0 && i.uvi < 8.0){
                showNotification("El "+CommonUtils.getFullDate(i.dt)+" puedes tomar el sol",
                    "Habra un uv de: ${i.uvi}")
            }
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


    inner class DownloadWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

        override fun doWork(): ListenableWorker.Result {
            repeat(1) {
                try {
                    setupObservers()
                } catch (e: IOException) {
                    return Result.failure()
                }
            }

            return ListenableWorker.Result.success()
        }
    }

}






