package com.example.composemed.domain.usecases

import com.example.composemed.domain.model.models.Medication
import com.example.composemed.domain.repository.LocalMedicationRepository
import javax.inject.Inject

class SaveMedicineUseCase @Inject constructor(
    private val repository: LocalMedicationRepository
) {

    suspend fun execute(medication: Medication) {
        return repository.saveMedication(medication = medication)
    }


}


