package com.example.composemed.home.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize



@Parcelize
data class Medication(
    val name: String,
    val dose: String,
    val strength: String,
    val description: String,
    val scientificName: String,
    val publisher: String
): Parcelable


