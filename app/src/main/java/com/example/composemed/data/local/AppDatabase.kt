package com.example.composemed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composemed.domain.model.entities.MedicationEntity

@Database(entities = [MedicationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicationDao(): MedicationDao

}
