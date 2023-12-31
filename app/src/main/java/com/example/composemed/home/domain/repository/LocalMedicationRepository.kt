package com.example.composemed.home.domain.repository

import com.example.composemed.home.domain.model.Medication

interface LocalMedicationRepository {

    suspend fun saveMedication(medication: Medication)
    suspend fun getAllMedications(): List<Medication>
}
