package com.example.composemed.home.data.repository

import com.example.composemed.home.data.remote.models.toDomainModel
import com.example.composemed.home.data.remote.ApiHealthService
import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.domain.repository.MedicationRepository


class MedicationRepositoryImpl(private val apiService: ApiHealthService) : MedicationRepository {

    override suspend fun getMedications(): List<Medication> {
        return apiService.getMedications().medications.map { it.toDomainModel() }
    }
}

