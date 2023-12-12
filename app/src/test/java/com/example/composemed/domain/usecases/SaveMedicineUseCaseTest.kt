package com.example.composemed.domain.usecases

import com.example.composemed.domain.model.models.Medication
import com.example.composemed.domain.repository.LocalMedicationRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SaveMedicineUseCaseTest {

    @Mock
    private lateinit var mockLocalRepository: LocalMedicationRepository

    private lateinit var saveMedicineUseCase: SaveMedicineUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        saveMedicineUseCase = SaveMedicineUseCase(mockLocalRepository)
    }

    @Test
    fun `execute calls local repository to save medication`() = runBlocking {
        val medication = Medication("Med1", "10mg", "Strong", "Description1", "Scientific1", "Publisher1")

        saveMedicineUseCase.execute(medication)

        Mockito.verify(mockLocalRepository).saveMedication(medication)
    }
}
