package com.example.composemed.home.data.repository

import com.example.composemed.common.AppDispatcher
import com.example.composemed.common.Dispatcher
import com.example.composemed.home.data.local.MedicationDao
import com.example.composemed.home.data.local.entities.MedicationEntity
import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.domain.repository.LocalMedicationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalMedicationRepositoryImp (
    private val medicationDao: MedicationDao,
    private val ioDispatcher: CoroutineDispatcher,

    ) : LocalMedicationRepository {
    override suspend fun saveMedication(medication: Medication) =
        withContext(ioDispatcher) { medicationDao.insertMedication(medication.toEntity()) }


    override suspend fun getAllMedications(): List<Medication> {
        return medicationDao.getAllMedications().map { it.toDomainModel() }
    }


}


//Todo separate as a mapper file
private fun Medication.toEntity(): MedicationEntity {
    return MedicationEntity(
        name = this.name,
        dose = this.dose,
        strength = this.strength,
        description = this.description,
        scientificName = this.scientificName,
        publisher = this.publisher,
    )
}

private fun MedicationEntity.toDomainModel(): Medication {
    return Medication(
        name = this.name,
        dose = this.dose,
        strength = this.strength,
        description = this.description,
        scientificName = this.scientificName,
        publisher = this.publisher,
    )
}

