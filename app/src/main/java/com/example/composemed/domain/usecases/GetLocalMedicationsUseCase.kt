package com.example.composemed.domain.usecases

import com.example.composemed.domain.model.models.Medication
import com.example.composemed.domain.repository.LocalMedicationRepository
import com.example.composemed.domain.repository.MedicationRepository
import javax.inject.Inject

class GetSavedMedicationsUseCase @Inject constructor(
    private val repository: LocalMedicationRepository
) {

    suspend fun execute(): List<Medication> {
        return repository.getAllMedications()
    }


}


