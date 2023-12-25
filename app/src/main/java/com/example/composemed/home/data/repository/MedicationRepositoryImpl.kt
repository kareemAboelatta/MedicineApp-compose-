package com.example.composemed.home.data.repository

import com.example.composemed.common.AppDispatcher
import com.example.composemed.common.Dispatcher
import com.example.composemed.home.data.remote.models.toDomainModel
import com.example.composemed.home.data.remote.ApiHealthService
import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.domain.repository.MedicationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class MedicationRepositoryImpl(
    private val apiService: ApiHealthService,
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    ) : MedicationRepository {

    override suspend fun getMedications(): List<Medication> {
        return withContext(ioDispatcher) {
            apiService.getMedications().medications.map { it.toDomainModel() }
        }
    }
}

