package com.example.runimprove

import android.annotation.SuppressLint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.runimprove.databinding.ItemEntrenoBinding

class EntrenoAdapter(var entrenoList: MutableList<Entreno>, private val listener: OnClickListener)
    :RecyclerView.Adapter<EntrenoAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemEntrenoBinding.bind(view)

        fun setListener(entreno: Entreno){
            binding.root.setOnLongClickListener{
                listener.onLongClick(entreno)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entreno,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entreno = entrenoList.get(position)
        holder.setListener(entreno)
        holder.binding.tvTipo.text = entreno.tipo
        holder.binding.tvPorcentaje.text = entreno.porcentaje.toString()
        holder.binding.tvFecha.text = entreno.fecha

    }

    override fun getItemCount(): Int = entrenoList.size

    @SuppressLint("NotifyDataSetChanged")
    fun remove(entreno: Entreno){
        entrenoList.remove(entreno)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeAll(){
        entrenoList.clear()
        notifyDataSetChanged()
    }


}