package com.example.composemed.home.domain.usecases

import com.example.composemed.home.domain.model.models.Medication
import com.example.composemed.home.domain.repository.LocalMedicationRepository
import com.example.composemed.home.domain.repository.MedicationRepository
import javax.inject.Inject

class GetSavedMedicationsUseCase @Inject constructor(
    private val repository: LocalMedicationRepository
) {

    suspend fun execute(): List<Medication> {
        return repository.getAllMedications()
    }


}


