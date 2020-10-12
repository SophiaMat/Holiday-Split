package com.example.holydaysplit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*


const val PERSONS = "com.example.holydaysplit.LIST"
class MainActivity : AppCompatActivity() {

    var persons = mutableListOf<Partner>()
    val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        persons = repository.retrieveData(this) as MutableList<Partner>
        if(persons.isNullOrEmpty()){
            showStartdataScreen()
        }
        else{
            setNames()
            setMoneyAppstart()
            setMoneyAppstart()
        }
    }

    fun showStartdataScreen(){
        val intent = Intent(this, StartDataActivity::class.java).apply{
        }
        startActivity(intent)
    }

    fun showMoneySpendingScreen(view: View){
        val intent = Intent(this, SpendMoneyActivity::class.java).apply{
            //val passPersons = persons as ArrayList<Partner>
            //putExtra(PERSONS, passPersons)
        }
        startActivity(intent)
    }

    private fun setNames(){
        Partner1.text = persons[0].name
        radioButton1.text = persons[0].name

        Partner2.text = persons[1].name
        radioButton2.text = persons[1].name
    }

    private fun setMoneyAppstart() {
        startingValue1.text = persons[0].startingMoney.toString() + "€"
        startingValue2.text = persons[1].startingMoney.toString() + "€"

        setCurrentMoney()
    }

    private fun setCurrentMoney(){
        currentStatus1.text = persons[0].moneyToSpend.toString() + "€"
        currentStatus2.text = persons[1].moneyToSpend.toString() + "€"
        val total =  persons[0].moneyToSpend + persons[1].moneyToSpend
        totalState.text = total.toString()
    }

    fun deleteAllData(view: View){
        repository.deleteAllData(this)
        showStartdataScreen()
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
