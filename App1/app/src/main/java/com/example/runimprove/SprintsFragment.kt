package com.example.runimprove

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.hardware.TriggerEvent
import android.hardware.TriggerEventListener
import android.media.RingtoneManager
import android.media.RingtoneManager.getRingtone
import android.os.Bundle
import android.os.CountDownTimer
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import com.example.runimprove.databinding.FragmentSprintsBinding
import androidx.core.content.ContextCompat.getSystemService

class SprintsFragment : Fragment() {


    private lateinit var mBinding : FragmentSprintsBinding
    private lateinit var sensorManager: SensorManager

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
       /* val enlace: TextView = mBinding.twEnalce
        enlace.movementMethod = LinkMovementMethod.getInstance()*/



        this.sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.let {
            var accelerometer = it
        }


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
                        val r = getRingtone(activity, notificacion)
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
                        val r = getRingtone(activity, notificacion)
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
                    val r = getRingtone(activity, notificacion)
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
        activity?.supportActionBar?.title = "Entrenar"
        super.onDestroy()
    }





}