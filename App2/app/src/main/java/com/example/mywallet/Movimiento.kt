package com.example.mywallet
import java.time.LocalDate.now


data class Movimiento(var id: Long = 0,
                      var tipo: String = "",
                      var categoria: String = "",
                      var cantidad: Double = 0.0,
                      var fecha: String = now().toString())

