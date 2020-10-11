package com.example.holydaysplit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*


const val STARTUP_DATA = "com.example.holydaysplit.DATA"

class MainActivity : AppCompatActivity() {

    var persons = mutableListOf<Partner>()
    val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        persons = repository.retrieveData(this) as MutableList<Partner>
        if(persons.isNullOrEmpty()){
            showStartdataScreen()
            val person1 = Partner("Tom")
            val person2 = Partner("Sophia")
            persons.add(person1)
            persons.add(person2)
            setStartingMoney(persons[0], 300.0)
            setStartingMoney(persons[1], 400.0)
            persons[0].moneyToSpend = persons[0].startingMoney
            persons[1].moneyToSpend = persons[1].startingMoney
        }
        setNames()
        setMoneyAppstart()
        setMoneyAppstart()
    }

    fun showStartdataScreen(){
        val intent = Intent(this, StartDataActivity::class.java).apply{
            val test = "test"
            putExtra(STARTUP_DATA, test)
        }
        startActivity(intent)
    }

    private fun setNames(){
        Partner1.text = persons[0].name
        radioButton1.text = persons[0].name

        Partner2.text = persons[1].name
        radioButton2.text = persons[1].name
    }

    private fun setStartingMoney(person: Partner, money: Double){
        person.startingMoney = money
    }

    private fun setMoneyAppstart() {
        startingValue1.text = persons[0].startingMoney.toString() + "€"
        startingValue2.text = persons[1].startingMoney.toString() + "€"

        setCurrentMoney()
    }

    private fun setCurrentMoney(){
        currentStatus1.text = persons[0].moneyToSpend.toString() + "€"
        currentStatus2.text = persons[1].moneyToSpend.toString() + "€"
    }

    fun deleteAllData(view: View){
        repository.deleteAllData(this)
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
        bringViewToStatusBack()
        repository.saveData(persons, this)
    }

    private fun calculateNewCurrentStatus(person: Partner, money: Double){
        person.moneyToSpend = person.moneyToSpend - money
        setCurrentMoney()
    }

    private fun bringViewToStatusBack(){
        moneySpent.setText("")
        radioGroup.clearCheck()
    }

}
