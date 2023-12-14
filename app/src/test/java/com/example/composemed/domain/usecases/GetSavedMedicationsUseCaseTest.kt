package com.example.composemed.domain.usecases

import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.domain.repository.LocalMedicationRepository
import com.example.composemed.home.domain.usecases.GetSavedMedicationsUseCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

import org.junit.Assert.assertTrue



class GetSavedMedicationsUseCaseTest {

    @Mock
    private lateinit var mockLocalRepository: LocalMedicationRepository

    private lateinit var getSavedMedicationsUseCase: GetSavedMedicationsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getSavedMedicationsUseCase = GetSavedMedicationsUseCase(mockLocalRepository)
    }

    @Test
    fun `execute returns non-empty list`() = runBlocking {
        val mockMedications = listOf(Medication("Med1", "10mg", "Strong", "Description1", "Scientific1", "Publisher1"))
        Mockito.`when`(mockLocalRepository.getAllMedications()).thenReturn(mockMedications)

        val result = getSavedMedicationsUseCase.execute()

        Mockito.verify(mockLocalRepository).getAllMedications()
        assertEquals(mockMedications, result)
    }

    @Test
    fun `execute returns empty list`() = runTest {
        Mockito.`when`(mockLocalRepository.getAllMedications()).thenReturn(emptyList())

        val result = getSavedMedicationsUseCase.execute()

        Mockito.verify(mockLocalRepository).getAllMedications()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `execute handles large data set`() = runTest {
        val largeListOfMedications = (1..1000).map {
            Medication("Med$it", "${it}mg", "Strength$it", "Description$it", "Scientific$it", "Publisher$it")
        }
        Mockito.`when`(mockLocalRepository.getAllMedications()).thenReturn(largeListOfMedications)

        val result = getSavedMedicationsUseCase.execute()

        Mockito.verify(mockLocalRepository).getAllMedications()
        assertEquals(largeListOfMedications, result)
    }

}
