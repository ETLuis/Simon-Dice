package com.dam2.simondice

import androidx.room.*

@Database(version = 1, entities = [Entity::class])

abstract class MusicDatabase : RoomDatabase() {
    abstract fun getId(): Entity
    abstract fun getNombre(): Entity
    abstract fun getRonda(): Entity
    abstract fun getRecord(): Entity
}