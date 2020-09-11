package com.webers.applock.data.database.pattern

import androidx.room.TypeConverter
import com.google.gson.Gson

class PatternTypeConverter {

    @TypeConverter
    fun patternToString(patternMetadata: PatternDotMetadata): String {
        return Gson().toJson(patternMetadata)
    }

    @TypeConverter
    fun stringToPattern(patternJson: String): PatternDotMetadata {
        return Gson().fromJson(patternJson, PatternDotMetadata::class.java)
    }
}