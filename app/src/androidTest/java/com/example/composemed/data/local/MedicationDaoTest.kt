package com.example.composemed.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composemed.home.domain.model.entities.MedicationEntity
import org.junit.runner.RunWith
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.composemed.home.data.local.AppDatabase
import com.example.composemed.home.data.local.MedicationDao

import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
@SmallTest
class MedicationDaoTest {



    private lateinit var database: AppDatabase
    private lateinit var dao: MedicationDao

    @Before
    fun setup() {
        // Using an in-memory database because the information stored here disappears after test
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.medicationDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertMedication_savesData() = runTest {
        val medication = MedicationEntity(id = 22, name = "TestMed", dose = "20mg",
            strength = "Strong", description = "Test description",
            scientificName = "TestScientific", publisher = "TestPublisher")
        dao.insertMedication(medication)
        println("insertMedication")

        val allMedications = dao.getAllMedications()
        assertTrue(allMedications.contains(medication))



    }

    @Test
    fun getAllMedications_returnsAllData() = runTest {
        val medication1 = MedicationEntity(id= 1 , name = "TestMed1", dose = "10mg",
            strength = "Medium", description = "Description1",
            scientificName = "Scientific1", publisher = "Publisher1")
        val medication2 = MedicationEntity(id= 2 ,name = "TestMed2", dose = "30mg",
            strength = "High", description = "Description2",
            scientificName = "Scientific2", publisher = "Publisher2")

        dao.insertMedication(medication1)
        dao.insertMedication(medication2)

        val allMedications = dao.getAllMedications()
        assertEquals(allMedications.size, 2)
        assertTrue(allMedications.contains(medication1))
        assertTrue(allMedications.contains(medication2))
    }
}
