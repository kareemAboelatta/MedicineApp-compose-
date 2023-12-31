package com.example.composemed.domain.usecases

import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.domain.repository.LocalMedicationRepository
import com.example.composemed.home.domain.usecases.SaveMedicineUseCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
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
    fun `execute calls local repository to save medication`() = runTest {
        val medication = Medication("Med1", "10mg", "Strong", "Description1", "Scientific1", "Publisher1")

        saveMedicineUseCase.execute(medication)

        Mockito.verify(mockLocalRepository).saveMedication(medication)
    }


    @Test
    fun `execute saves different medications correctly`() = runTest {
        val medication1 = Medication("Med1", "10mg", "Strong", "Description1", "Scientific1", "Publisher1")
        val medication2 = Medication("Med2", "20mg", "Mild", "Description2", "Scientific2", "Publisher2")

        saveMedicineUseCase.execute(medication1)
        saveMedicineUseCase.execute(medication2)

        Mockito.verify(mockLocalRepository).saveMedication(medication1)
        Mockito.verify(mockLocalRepository).saveMedication(medication2)
    }



    

    @Test(expected = Exception::class)
    fun `execute throws exception when repository encounters an error`() = runTest {
        val medication = Medication("Med1", "10mg", "Strong", "Description1", "Scientific1", "Publisher1")
        val exception = RuntimeException("Error")

        Mockito.doThrow(exception).`when`(mockLocalRepository).saveMedication(medication)

        saveMedicineUseCase.execute(medication)

        Mockito.verify(mockLocalRepository).saveMedication(medication)
    }




}
