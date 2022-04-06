package com.example.runimprove

import java.sql.Date
import java.time.LocalDate
import java.time.LocalDate.now

data class Entreno(
    var id: Long = 0, var tipo: String = "", var porcentaje: Double = 0.0,
    var fecha: String = now().toString())
