package com.dam2.simondice

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Entity::class])

abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}