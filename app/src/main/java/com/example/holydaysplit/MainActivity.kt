package com.example.holydaysplit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val persons = mutableListOf<Partner>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val person1 = Partner("Tom")
        val person2 = Partner("Sophia")
        persons.add(person1)
        persons.add(person2)
        setNames()
        setStartingMoney(person1, 300.0)
        setStartingMoney(person2, 400.0)
    }

    private fun setNames(){
        Partner1.text = persons[0].name
        radioButton1.text = persons[0].name

        Partner2.text = persons[1].name
        radioButton2.text = persons[1].name
    }

    private fun setStartingMoney(partner: Partner, startingMoney: Double) {
        partner.startingMoney = startingMoney
        startingValue1.text = persons[0].startingMoney.toString() + "€"
        startingValue2.text = persons[1].startingMoney.toString() + "€"

        persons[0].moneyToSpend = persons[0].startingMoney
        persons[1].moneyToSpend = persons[1].startingMoney
        setCurrentMoney()
    }

    private fun setCurrentMoney(){
        currentStatus1.text = persons[0].moneyToSpend.toString() + "€"
        currentStatus2.text = persons[1].moneyToSpend.toString() + "€"
    }

    fun setNewMoneySpentValue(view: View) {
        var money = moneySpent.text.toString()
        var moneyparsed = money.toDoubleOrNull()
        var id = radioGroup.checkedRadioButtonId
        if(id!=-1){
            if(moneyparsed != null){
                val radio: RadioButton = findViewById<RadioButton>(id)
                for(item in persons){
                    if (item.name == radio.text.toString()){
                        calculateNewCurrentStatus(item, moneyparsed)
                    }
                }
            }
        }

    }

    private fun calculateNewCurrentStatus(person: Partner, money: Double){
        person.moneyToSpend = person.moneyToSpend - money
        setCurrentMoney()
    }

}
