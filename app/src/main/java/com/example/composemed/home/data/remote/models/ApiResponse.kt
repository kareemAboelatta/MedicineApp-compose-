package com.example.composemed.home.data.remote.models

import com.example.composemed.home.domain.model.Medication

data class ApiResponse(
    val medications: List<ApiMedicationModel>
)

data class ApiMedicationModel(
    val name: String,
    val dose: String,
    val strength: String,
    val description: String,
    val scientificName: String,
    val publisher: String

)



//Todo separate as a mapper file
fun ApiMedicationModel.toDomainModel(): Medication {
    return Medication(
        name = name,
        dose = dose,
        strength = strength,
        description = description,
        scientificName = scientificName,
        publisher = publisher,

    )
}
