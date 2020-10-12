package com.example.holydaysplit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_spend_money.*

class SpendMoneyActivity : AppCompatActivity() {

    val repository = Repository()
    var persons = mutableListOf<Partner>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spend_money)
        setNamesRadioButton()
    }

    fun setNewMoneySpentValue(view: View) {
        var money = moneySpent.text.toString()
        var moneyparsed = money.toDoubleOrNull()
        var id = radioGroup.checkedRadioButtonId
        if(id!=-1){
            if(moneyparsed != null){
                val radio: RadioButton = findViewById<RadioButton>(id)
                for(item in MainActivity().persons){
                    if (item.name == radio.text.toString()){
                        calculateNewCurrentStatus(item, moneyparsed)
                    }
                }
            }
        }
        repository.saveData(MainActivity().persons, this)
    }

    private fun calculateNewCurrentStatus(person: Partner, money: Double){
        person.moneyToSpend = person.moneyToSpend - money
        MainActivity().setCurrentMoney()
    }

    private fun setNamesRadioButton(){
        persons = repository.retrieveData(this) as MutableList<Partner>
        radioButton1.text =  persons[0].name.toString()

        radioButton2.text = persons[1].name
    }
}