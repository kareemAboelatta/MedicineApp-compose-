package com.example.composemed.data.models

import com.example.composemed.domain.model.Medication

data class ApiResponse(
    val medications: List<ApiMedicationModel>
)

data class ApiMedicationModel(
    val name: String,
    val dose: String,
    val strength: String,
    val description: String,
    val scientificName: String
)



fun ApiMedicationModel.toDomainModel():Medication{
    return Medication(
        name = name,
        dose = dose,
        strength = strength,
        description = description,
        scientificName = scientificName,
    )
}