package com.example.composemed.domain.usecases

import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.domain.repository.MedicationRepository
import com.example.composemed.home.domain.usecases.GetRemoteMedicationsUseCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
    fun `execute calls repository and returns data`() = runTest {
        val mockMedications = listOf(
            Medication("Med1", "10mg", "Strong", "Description1", "Scientific1", "Publisher1")

        )
        Mockito.`when`(mockRepository.getMedications()).thenReturn(mockMedications)

        val result = getRemoteMedicationsUseCase.execute()

        Mockito.verify(mockRepository).getMedications()

        assertEquals(mockMedications, result)
    }


    @Test
    fun `execute returns empty list when repository has no data`() = runTest {
        Mockito.`when`(mockRepository.getMedications()).thenReturn(emptyList())

        val result = getRemoteMedicationsUseCase.execute()

        Mockito.verify(mockRepository).getMedications()

        assertTrue(result.isEmpty())
    }




    @Test(expected = Exception::class)
    fun `execute throws exception when repository encounters an error`() = runTest {
        val exception = RuntimeException("Error")
        Mockito.`when`(mockRepository.getMedications()).thenThrow(exception)

        getRemoteMedicationsUseCase.execute()

        Mockito.verify(mockRepository).getMedications()
    }




}
