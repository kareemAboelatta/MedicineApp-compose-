package com.example.composemed.domain.usecases

import com.example.composemed.domain.model.Medication
import com.example.composemed.domain.repository.MedicationRepository
import javax.inject.Inject

class GetMedicationsUseCase @Inject constructor(
    private val repository: MedicationRepository
) {

    suspend fun execute(): List<Medication> {
        return repository.getMedications()
    }


}


