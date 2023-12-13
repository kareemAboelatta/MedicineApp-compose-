package com.example.composemed.home.domain.repository

import com.example.composemed.home.domain.model.models.Medication


interface MedicationRepository {
    suspend fun getMedications(): List<Medication>
}
