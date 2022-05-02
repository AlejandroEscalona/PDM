package com.example.mywallet


import android.annotation.SuppressLint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mywallet.databinding.ItemMovimientoBinding

class MovimientoAdapter(var movimientoList: MutableList<Movimiento>, private val listener: OnClickListener)
    :RecyclerView.Adapter<MovimientoAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemMovimientoBinding.bind(view)

        fun setListener(movimiento: Movimiento){
            binding.root.setOnLongClickListener{
                listener.onLongClick(movimiento)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movimiento,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movimiento = movimientoList.get(position)
        holder.setListener(movimiento)
        holder.binding.itemTipo.text = movimiento.tipo
        holder.binding.itemCategoria.text = movimiento.categoria
        holder.binding.itemCantidad.text = movimiento.cantidad.toString()+"€"
        holder.binding.itemFecha.text = movimiento.fecha

    }

    override fun getItemCount(): Int = movimientoList.size

    fun getIndex(index: Int): Movimiento = movimientoList[index]


    @SuppressLint("NotifyDataSetChanged")
    fun remove(movimiento: Movimiento){
        movimientoList.remove(movimiento)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun remove(index: Int){
        val movimiento = movimientoList[index]
        movimientoList.remove(movimiento)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeAll(){
        movimientoList.clear()
        notifyDataSetChanged()
    }


}