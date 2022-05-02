package com.example.mywallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywallet.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var database: DatabaseHelper
    private lateinit var movimientoAdapter: MovimientoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container,false)
        return mBinding.root

    }

//    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(itemView, savedInstanceState)
//        val data = database.getAllMovimientos()
//        movimientoAdapter = MovimientoAdapter(data,this)
//        mBinding.recyclerViewMovimientos.apply {
//            // set a LinearLayoutManager to handle Android
//            // RecyclerView behavior
//            layoutManager = LinearLayoutManager(activity)
//            // set the custom adapter to the RecyclerView
//            adapter = movimientoAdapter
//        }
//    }

    fun onLongClick(movimiento: Movimiento) {
        TODO("Not yet implemented")
    }

//    private fun getMovimientos(){
//        val data = database.getAllMovimientos()
//        movimientoAdapter = MovimientoAdapter(data,this)
//        mBinding.recyclerViewMovimientos.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = movimientoAdapter
//        }
//    }



}