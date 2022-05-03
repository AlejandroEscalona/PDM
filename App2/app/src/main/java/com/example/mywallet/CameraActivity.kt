package com.example.mywallet

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mywallet.databinding.ActivityCameraBinding
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private lateinit var imageCapture: ImageCapture
    private lateinit var cameraExecutor: ExecutorService
    private var scanner = Scanner()
    private lateinit var database: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)


        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);


        if(allPermissionsGranted()){
            startCamera()
        } else{
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        binding.captureButton.setOnClickListener { takePhoto() }


        cameraExecutor = Executors.newSingleThreadExecutor()
        imageCapture = ImageCapture.Builder().setFlashMode(ImageCapture.FLASH_MODE_AUTO).build()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    /**
     * Obtener foto
     *
     * @param action contexto en el que se ha abierto la cámara: Barcode o Price
     */
    private fun takePhoto() {
        imageCapture.takePicture(cameraExecutor,
            object: ImageCapture.OnImageCapturedCallback(){ //Se llama cuando capturamos una imagen
                override fun onError(exception: ImageCaptureException) { // Si hay errores
                    Log.e("CamerApp","Error en la captura de la imagen", exception)
                }

                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)
                    //var data = Intent()

                    val recognizedText = scanner.analyzeText(image,image.imageInfo.rotationDegrees)
                    image.close()
                    val array: ArrayList<String> = ArrayList(recognizedText.asList())



                    val inputCantidad = (((recognizedText.last())
                        .replace(",", "."))
                        .slice(8 until recognizedText.last().length)
                        .toDouble()) * -1
                    val inputCategoria = "Supermercado"

                    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

                    val id = database.insertMovimiento("Gastos",inputCategoria,
                        inputCantidad,(LocalDate.now().format(formatter)).toString())
                    finish()
                    if(id != -1L ){
                        Snackbar.make(binding.root, getString(R.string.guardar_exito), Snackbar.LENGTH_SHORT).show()
                    } else Snackbar.make(binding.root, getString(R.string.error_guardar), Snackbar.LENGTH_SHORT).show()
                }
            }
        )
    }

    /**
     * Abrir la cámara para hacer una foto
     */
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also { it.setSurfaceProvider(binding.viewFinder.surfaceProvider) }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try{
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            }
            catch(exc: Exception){
                Log.e("Mywallet", "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    /**
     * Comprobar si se han obtenido todos los permisos
     */
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == REQUEST_CODE_PERMISSIONS) {
            if(allPermissionsGranted()){
                startCamera()
            } else {
                Toast.makeText(this, "Permissions not granted by the user", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = mutableListOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).toTypedArray()
    }
}


