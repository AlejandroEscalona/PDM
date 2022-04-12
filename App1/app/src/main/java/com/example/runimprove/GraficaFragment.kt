package com.example.runimprove

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.runimprove.databinding.FragmentGraficaBinding


class GraficaFragment : Fragment() {

    private lateinit var mBinding : FragmentGraficaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = FragmentGraficaBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as? HistoricalActivity
        //activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.supportActionBar?.title = "Gráfica de entrenamientos"
    }

    override fun onDestroy() {
        val activity = activity as? HistoricalActivity
        activity?.supportActionBar?.title = getString(R.string.historial_de_entrenamientos)
        super.onDestroy()
    }



}