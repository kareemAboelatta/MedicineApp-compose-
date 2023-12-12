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
            Medication("Med1", "10mg", "Strong", "Description1", "Scientific1", "Publisher1"),
            Medication("Kareem Med1", "1220mg", "Strong", "Description2", "Scientific1", "Publisher1"),

        )

        // when getAllMedications() called, it will return the predefined list `mockMedications`.
        Mockito.`when`(mockLocalRepository.getAllMedications()).thenReturn(mockMedications)

        val result = getSavedMedicationsUseCase.execute()


        //The purpose of this verification is to ensure that the GetSavedMedicationsUseCase is correctly
        // calling the getAllMedications() method on its dependency (which in this case is
        // the mocked LocalMedicationRepository)
        Mockito.verify(mockLocalRepository).getAllMedications()

        println("Checking the mockLocalRepository :${mockLocalRepository.getAllMedications().size}")



        assertEquals(mockMedications, result)
    }
}
