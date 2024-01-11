package com.example.starwars.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [PersonEntity::class],
    version = 1
)
@TypeConverters(FilmsListConverter::class)

abstract class PersonDB : RoomDatabase() {
    abstract val dao: PersonDao
}