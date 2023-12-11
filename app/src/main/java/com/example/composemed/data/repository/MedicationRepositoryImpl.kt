package com.example.composemed.data.repository

import com.example.composemed.data.models.toDomainModel
import com.example.composemed.data.remote.ApiHealthService
import com.example.composemed.domain.model.Medication
import com.example.composemed.domain.repository.MedicationRepository


class MedicationRepositoryImpl(private val apiService: ApiHealthService) : MedicationRepository {

    override suspend fun getMedications(): List<Medication> {
        return apiService.getMedications().medications.map { it.toDomainModel() }
    }
}

