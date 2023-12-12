package com.example.composemed.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composemed.domain.model.entities.MedicationEntity

@Dao
interface MedicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedication(medication: MedicationEntity)

    @Query("SELECT * FROM MedicationEntity")
    suspend fun getAllMedications(): List<MedicationEntity>
}