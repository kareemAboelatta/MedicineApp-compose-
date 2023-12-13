package com.example.composemed.home.domain.usecases

import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.domain.repository.MedicationRepository
import javax.inject.Inject


//Tested
class GetRemoteMedicationsUseCase @Inject constructor(
    private val repository: MedicationRepository
) {

    suspend fun execute(): List<Medication> {
        return repository.getMedications()
    }


}


