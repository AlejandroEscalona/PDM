package com.example.runimprove

import android.media.RingtoneManager
import android.media.RingtoneManager.getRingtone
import android.os.Bundle
import android.os.CountDownTimer
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

        mBinding.btnPlay.setOnClickListener {
            object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    mBinding.tvCuentaAtras.setText("Segundos Restantes: " + millisUntilFinished / 1000)
                    mBinding.btnPause.setOnClickListener{
                        mBinding.tvCuentaAtras.setText("")
                        cancel()
                    }
                }
                override fun onFinish() {
                    val notificacion=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                    val r= getRingtone(activity,notificacion)
                    r.play()
                    this.cancel()
                    mBinding.btnStopMusic.setOnClickListener{
                        r.stop()
                    }
                }
            }.start()
        }


    }

    override fun onDestroy() {
        val activity = activity as? TrainingActivity
        activity?.supportActionBar?.title = "Entrenar"
        super.onDestroy()
    }



    fun play(view: View){
//        var txtTiempo=mBinding.tvCuentaAtras.text.toString().toLong()
//        var tvCuentaAtras=mBinding.tvCuentaAtras
//        var tiempoSegundos= txtTiempo.toString().toLong()
//        var tiempoMilisegundos=tiempoSegundos*1000
//        object : CountDownTimer(tiempoMilisegundos,1000){
//            override fun onFinish() {
//                val notificacion=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
//                val r= getRingtone(activity,notificacion)
//                r.play()
//                this.cancel()
//            }
//            override fun onTick(millisUntilFinished: Long) {
//                //val tiempoSegundos=(millisUntilFinished/1000).toInt()+1
//                tvCuentaAtras?.text="0"
//            }
//        }.start()



    }


}