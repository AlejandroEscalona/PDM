package com.example.runimprove

import android.annotation.SuppressLint
import android.media.RingtoneManager
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.runimprove.databinding.FragmentHitBinding


class HitFragment : Fragment() {

    private lateinit var mBinding : FragmentHitBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = FragmentHitBinding.inflate(inflater,container,false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as? TrainingActivity
        //activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.supportActionBar?.title = getString(R.string.entrenamiento_de_hit)

        mBinding.btnPlay.setOnClickListener {
            object : CountDownTimer(20000, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    mBinding.tvCuentaAtras.setText("" + millisUntilFinished / 1000)
                    mBinding.btnPause.setOnClickListener {
                        mBinding.tvCuentaAtras.setText("")
                        cancel()
                    }
                }
                override fun onFinish() {
                    val notificacion =
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                    val r = RingtoneManager.getRingtone(activity, notificacion)
                    r.play()
                    this.cancel()
                    mBinding.btnStopMusic.setOnClickListener {
                        r.stop()
                    }
                }
            }.start()
        }


        mBinding.btnPlay2.setOnClickListener {
            object : CountDownTimer(120000, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    mBinding.tvCuentaAtras2.setText("" + millisUntilFinished / 1000)
                    mBinding.btnPause.setOnClickListener {
                        mBinding.tvCuentaAtras2.setText("")
                        cancel()
                    }
                }

                override fun onFinish() {
                    val notificacion =
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                    val r = RingtoneManager.getRingtone(activity, notificacion)
                    r.play()
                    this.cancel()
                    mBinding.btnStopMusic.setOnClickListener {
                        r.stop()
                    }
                }
            }.start()
        }

        mBinding.btnPlay3.setOnClickListener {
            object : CountDownTimer(120000, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    mBinding.tvCuentaAtras3.setText("" + millisUntilFinished / 1000)
                    mBinding.btnPause.setOnClickListener {
                        mBinding.tvCuentaAtras3.setText("")
                        cancel()
                    }
                }

                override fun onFinish() {
                    val notificacion =
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                    val r = RingtoneManager.getRingtone(activity, notificacion)
                    r.play()
                    this.cancel()
                    mBinding.btnStopMusic.setOnClickListener {
                        r.stop()
                    }
                }
            }.start()
        }
    }

    override fun onDestroy() {
        val activity = activity as? TrainingActivity
        activity?.supportActionBar?.title = getString(R.string.entrenar)
        super.onDestroy()
    }

}