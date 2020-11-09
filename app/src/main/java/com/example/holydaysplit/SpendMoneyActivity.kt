package com.example.holydaysplit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.example.holydaysplit.Models.Item
import com.example.holydaysplit.Models.Partner
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
                for(person in persons){
                    if (person.name == radio.text.toString()){
                        calculateNewCurrentStatus(person, moneyparsed)
                    }
                }
            }
        }
        repository.saveData(persons, this)
        val intent = Intent(this, MainActivity::class.java).apply{
        }
        startActivity(intent)
    }

    private fun calculateNewCurrentStatus(person: Partner, price: Double){
        person.moneyToSpend = person.moneyToSpend - price
        createNewItem(person, price)
    }

    private fun createNewItem(person: Partner, price: Double) {
        if(itemDescription.text.toString().isNotEmpty()){
            val item = Item(itemDescription.text.toString())
            item.price = price
            person.items.add(item)
        }
    }

    private fun setNamesRadioButton(){
        persons = repository.retrieveData(this) as MutableList<Partner>
        radioButton1.text =  persons[0].name

        radioButton2.text = persons[1].name
    }
}