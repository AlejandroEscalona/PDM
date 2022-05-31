package com.example.vit_dapp.mainModule.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vit_dapp.common.entities.Forecast
import com.example.vit_dapp.databinding.ItemWeatherBinding
import com.example.vit_dapp.BR

class ForecastAdapter(private val listener: OnClickListener) :
    ListAdapter<Forecast,RecyclerView.ViewHolder>(ForecastDiffCallback()) {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemWeatherBinding>(view)

        fun setListener(forecast: Forecast){
            binding?.root?.setOnClickListener{
                listener.onClick(forecast)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(com.example.vit_dapp.R.layout.item_weather,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val forecast = getItem(position)

        with(holder as ViewHolder){
            holder.binding?.setVariable(BR.forecast, forecast)
            holder.binding?.executePendingBindings()

            setListener(forecast)
        }
    }

    class ForecastDiffCallback: DiffUtil.ItemCallback<Forecast>(){
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return oldItem == newItem
        }
    }


}