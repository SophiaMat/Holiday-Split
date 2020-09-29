package com.example.holydaysplit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val person1 = Partner("Tom")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValuePartner(300.0)
    }

    private fun setValuePartner(moneyToSpend: Double) {
        person1.moneyToSpend = moneyToSpend
    }
}
