package com.example.vit_dapp.common.utils
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {
    fun getHour(epoch: Long): String = getFormatedtime(epoch, "HH:mm")

    fun getFormatedtime(epoch: Long, pattern: String): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(epoch * 1_000)
    }
}