package com.example.composemed.domain.repository

import com.example.composemed.domain.model.models.Medication

interface LocalMedicationRepository {

    suspend fun saveMedication(medication: Medication)
    suspend fun getAllMedications(): List<Medication>
}
