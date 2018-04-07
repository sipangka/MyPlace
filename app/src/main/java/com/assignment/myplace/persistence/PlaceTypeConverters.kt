package com.assignment.myplace.persistence

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class PlaceTypeConverters {

    @TypeConverter
    fun stringToType(json: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {

        }.getType()
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun typeToString(list: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {

        }.getType()
        return gson.toJson(list, type)
    }
}