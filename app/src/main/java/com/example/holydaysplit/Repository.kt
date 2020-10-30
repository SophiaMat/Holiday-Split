package com.example.holydaysplit

import android.content.Context
import com.example.holydaysplit.Models.Partner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class Repository{
    var gson = Gson()

    fun saveData(persons: MutableList<Partner>, context: Context) {
        var jsonString = gson.toJson(persons)
        val file = File(context.getExternalFilesDir(null), "/location")
        if(!file.exists())
            file.mkdir()
        try {
            val jsonFile = File(file, "DB.json")
            val writer = FileWriter(jsonFile)
            writer.write(jsonString)
            writer.flush()
            writer.close()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun retrieveData(context: Context): List<Partner>? {
        val file = File(context.getExternalFilesDir(null), "/location")
        if(file.exists()){
            try {
                val jsonFile = File(file, "DB.json")
                val reader = FileReader(jsonFile)
                val objectType = object : TypeToken<List<Partner>>(){}.type
                var persons = gson.fromJson<List<Partner>>(reader, objectType)
                if(persons == null){
                    persons = mutableListOf<Partner>()
                }
                return persons
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
        val persons = mutableListOf<Partner>()
        return persons
    }

    fun deleteAllData(context: Context){
        val file = File(context.getExternalFilesDir(null), "/location")
        if(file.exists()){
            try {
                val jsonFile = File(file, "DB.json")
                val writer = FileWriter(jsonFile)
                writer.write("")
                writer.flush()
                writer.close()
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}