package com.example.holydaysplit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val person1 = Partner("Tom")
    val person2 = Partner("Sophia")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNames()
        setStartingMoney(person1, 300.0)
        setMoneyToSpend(person1, 300.0)
        setStartingMoney(person2, 400.0)
        setMoneyToSpend(person2, 400.0)
    }

    private fun setNames(){
        Partner1.text = person1.name
        radioButton1.text = person1.name

        Partner2.text = person2.name
        radioButton2.text = person2.name
    }

    private fun setMoneyToSpend(partner: Partner, moneyToSpend: Double) {
        partner.moneyToSpend = moneyToSpend
        currentStatus1.text = person1.moneyToSpend.toString() + "€"
        currentStatus2.text = person2.moneyToSpend.toString() + "€"
    }

    private fun setStartingMoney(partner: Partner, startingMoney: Double) {
        partner.startingMoney = startingMoney
        startingValue1.text = person1.startingMoney.toString() + "€"
        startingValue2.text = person2.startingMoney.toString() + "€"
    }

}
