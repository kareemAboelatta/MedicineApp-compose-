package com.example.composemed.domain.usecases

import com.example.composemed.home.domain.model.models.Medication
import com.example.composemed.home.domain.repository.MedicationRepository
import com.example.composemed.home.domain.usecases.GetRemoteMedicationsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetRemoteMedicationsUseCaseTest {

    @Mock
    private lateinit var mockRepository: MedicationRepository

    private lateinit var getRemoteMedicationsUseCase: GetRemoteMedicationsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getRemoteMedicationsUseCase = GetRemoteMedicationsUseCase(mockRepository)
    }

    @Test
    fun `execute calls repository and returns data`() = runBlocking {
        val mockMedications = listOf(
            Medication("Med1", "10mg", "Strong", "Description1", "Scientific1", "Publisher1")

        )
        Mockito.`when`(mockRepository.getMedications()).thenReturn(mockMedications)

        val result = getRemoteMedicationsUseCase.execute()

        Mockito.verify(mockRepository).getMedications()

        assertEquals(mockMedications, result)
    }
}
