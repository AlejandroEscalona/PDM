package com.example.runimprove

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.runimprove.databinding.FragmentSprintsBinding
import com.example.runimprove.databinding.FragmentTecnicaBinding

class TecnicaFragment : Fragment() {

    private lateinit var mBinding : FragmentTecnicaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = FragmentTecnicaBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as? TrainingActivity
        //activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.supportActionBar?.title = "Entrenar Técnica"
    }

    override fun onDestroy() {
        val activity = activity as? TrainingActivity
        activity?.supportActionBar?.title = "Entrenar"
        super.onDestroy()
    }

}