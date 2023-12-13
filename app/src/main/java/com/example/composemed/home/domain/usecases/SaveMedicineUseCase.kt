package com.example.composemed.home.domain.usecases

import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.domain.repository.LocalMedicationRepository
import javax.inject.Inject

class SaveMedicineUseCase @Inject constructor(
    private val repository: LocalMedicationRepository
) {

    suspend fun execute(medication: Medication) {
        return repository.saveMedication(medication = medication)
    }


}


