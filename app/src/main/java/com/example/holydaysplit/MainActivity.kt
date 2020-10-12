package com.example.holydaysplit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_spend_money.*
import java.io.File


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


    private fun setNames(){
        Partner1.text = persons[0].name

        Partner2.text = persons[1].name
    }

    fun showStartdataScreen(){
        val intent = Intent(this, StartDataActivity::class.java).apply{
        }
        startActivity(intent)
    }

    fun showMoneySpendingScreen(view: View){
        val intent = Intent(this, SpendMoneyActivity::class.java).apply{
        }
        startActivity(intent)
    }

    private fun setMoneyAppstart() {
        startingValue1.text = persons[0].startingMoney.toString() + "€"
        startingValue2.text = persons[1].startingMoney.toString() + "€"

        setCurrentMoney()
    }

    fun setCurrentMoney(){
        currentStatus1.text = persons[0].moneyToSpend.toString() + "€"
        currentStatus2.text = persons[1].moneyToSpend.toString() + "€"
        val total =  persons[0].moneyToSpend + persons[1].moneyToSpend
        totalState.text = total.toString()
    }

    fun deleteAllData(view: View){
        repository.deleteAllData(this)
        showStartdataScreen()
    }

}
