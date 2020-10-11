package com.example.holydaysplit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_start_data.*

class StartDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_data)
    }

    fun saveStartData(view: View){
        var persons = mutableListOf<Partner>()
        val person1 = Partner(Person1Name.text.toString())
        val person2 = Partner(Person2Name.text.toString())
        persons.add(person1)
        persons.add(person2)
        try{
            persons[0].startingMoney = Person1Value.text.toString().toDouble()
            persons[0].moneyToSpend = Person1Value.text.toString().toDouble()

            persons[1].startingMoney = Person2Value.text.toString().toDouble()
            persons[1].moneyToSpend = Person2Value.text.toString().toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}