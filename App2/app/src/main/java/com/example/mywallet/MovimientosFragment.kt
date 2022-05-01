package com.example.mywallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mywallet.databinding.FragmentMovimientosBinding

class MovimientosFragment : Fragment() {

    private lateinit var mBinding: FragmentMovimientosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movimientos, container, false)
    }
}