package com.example.composemed.domain.repository

import com.example.composemed.domain.model.Medication


interface MedicationRepository {
    suspend fun getMedications(): List<Medication>
}
