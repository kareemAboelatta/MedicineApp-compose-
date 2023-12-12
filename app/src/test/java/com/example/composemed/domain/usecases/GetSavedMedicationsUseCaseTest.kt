package com.example.composemed.domain.usecases

import com.example.composemed.domain.model.models.Medication
import com.example.composemed.domain.repository.LocalMedicationRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

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
    fun `execute calls local repository and returns data`() = runBlocking {
        val mockMedications = listOf(
            Medication("Med1", "10mg", "Strong", "Description1", "Scientific1", "Publisher1")
            // Add more mock medications as needed
        )
        Mockito.`when`(mockLocalRepository.getAllMedications()).thenReturn(mockMedications)

        val result = getSavedMedicationsUseCase.execute()

        Mockito.verify(mockLocalRepository).getAllMedications()
        assertEquals(mockMedications, result)
    }
}
