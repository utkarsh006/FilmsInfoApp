package com.example.starwars.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity
data class PersonEntity(
    @PrimaryKey
    val url: String,
    val birth_year: String,
    val gender: String,
    val name: String,
    @TypeConverters(FilmsListConverter::class)
    val films: List<String>,
    val next: String?
)

class FilmsListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun toString(list: List<String>): String {
        return list.joinToString(",")
    }
}
