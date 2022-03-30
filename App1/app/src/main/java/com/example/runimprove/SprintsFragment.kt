package com.example.runimprove

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.runimprove.databinding.FragmentSprintsBinding


class SprintsFragment : Fragment() {

    private lateinit var mBinding : FragmentSprintsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = FragmentSprintsBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as? TrainingActivity
        //activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.supportActionBar?.title = "Entrenar Sprints"

        val enlace: TextView = mBinding.twEnalce
        enlace.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onDestroy() {
        val activity = activity as? TrainingActivity
        activity?.supportActionBar?.title = "Entrenar"
        super.onDestroy()
    }

}