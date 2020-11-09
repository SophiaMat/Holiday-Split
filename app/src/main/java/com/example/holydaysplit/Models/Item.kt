package com.example.holydaysplit.Models
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

import java.time.format.DateTimeFormatter

data class Item (val name: String) {
    var price: Double? = 0.0
    @RequiresApi(Build.VERSION_CODES.O)
    val time = LocalDateTime.now()
}