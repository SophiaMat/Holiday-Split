package com.example.holydaysplit.Models

import kotlin.properties.Delegates

data class Partner(val name: String) {
    var moneyToSpend = 0.0
    var startingMoney = 0.0
    var items = mutableListOf<Item>()
}