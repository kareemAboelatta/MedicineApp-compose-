package com.example.composemed.data.repository

import com.example.composemed.data.local.MedicationDao
import com.example.composemed.domain.model.entities.MedicationEntity
import com.example.composemed.domain.model.models.Medication
import com.example.composemed.domain.repository.LocalMedicationRepository
import javax.inject.Inject

class LocalMedicationRepositoryImp @Inject constructor(
    private val medicationDao: MedicationDao
) : LocalMedicationRepository {
    override suspend fun saveMedication(medication: Medication) {
        medicationDao.insertMedication(medication.toEntity())
    }

    override suspend fun getAllMedications(): List<Medication> {
        return medicationDao.getAllMedications().map { it.toDomainModel() }
    }

    private fun Medication.toEntity(): MedicationEntity {
        return MedicationEntity(
            name = this.name,
            dose = this.dose,
            strength = this.strength,
            description = this.description,
            scientificName = this.scientificName
        )
    }

    private fun MedicationEntity.toDomainModel(): Medication {
        return Medication(
            name = this.name,
            dose = this.dose,
            strength = this.strength,
            description = this.description,
            scientificName = this.scientificName
        )
    }
}
