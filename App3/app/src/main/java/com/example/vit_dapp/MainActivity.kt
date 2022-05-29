package com.example.vit_dapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.vit_dapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Volley

    }

//    fun getUvIndex(){
//       val url = "https://api.openweathermap.org/data/2.5/onecall?lat=37.18817&lon=-3.60667&appid=51f76e6decc35f7045f21f0e1658d9fa"
//        val request = com.android.volley.toolbox.StringRequest(Request.Method.GET, url,
//            { response ->
//                Log.i("Response", response)
//               binding.test.text = response.toString()
//            },
//            { error ->
//                error.printStackTrace()
//            })
//        storeApi.addToRequestQueue(request)
//    }
}





