package com.nennos.pizza.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromIntsToString(ingredientIds: List<Int>?): String {
        return Gson().toJson(ingredientIds)
    }

    @TypeConverter
    fun fromStringToInts(data: String): List<Int>? {
        val objects = Gson().fromJson(data, Array<Int>::class.java) as Array<Int>
        return objects.toList()
    }
}