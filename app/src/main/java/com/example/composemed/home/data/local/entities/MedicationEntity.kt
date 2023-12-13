package com.example.composemed.home.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MedicationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dose: String,
    val strength: String,
    val description: String,
    val scientificName: String,
    val publisher: String
)