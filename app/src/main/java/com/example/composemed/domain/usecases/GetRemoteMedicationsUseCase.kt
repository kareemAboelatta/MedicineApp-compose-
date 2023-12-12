package com.example.composemed.domain.usecases

import com.example.composemed.domain.model.models.Medication
import com.example.composemed.domain.repository.MedicationRepository
import javax.inject.Inject

class GetRemoteMedicationsUseCase @Inject constructor(
    private val repository: MedicationRepository
) {

    suspend fun execute(): List<Medication> {
        return repository.getMedications()
    }


}


