package com.example.holydaysplit

import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Environment
import com.google.gson.Gson
import java.io.File
import java.io.FileWriter

class Repository{
    var gson = Gson()

    fun saveData(persons: MutableList<Partner>, context: Context) {
        var jsonString = gson.toJson(persons)
        //val file = File(context.getFilesDir(), "DB.json")
        //file.createNewFile()
        //File("DB.json").writeText(jsonString)

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
}