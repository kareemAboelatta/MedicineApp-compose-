package com.example.composemed.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composemed.home.data.local.entities.MedicationEntity
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
    fun should_save_a_medicine_when_insert_a_medicine() = runTest {
        val medication = MedicationEntity(id = 22, name = "TestMed", dose = "20mg",
            strength = "Strong", description = "Test description",
            scientificName = "TestScientific", publisher = "TestPublisher")
        dao.insertMedication(medication)

        val allMedications = dao.getAllMedications()


        assertTrue(allMedications.contains(medication))

    }

    @Test
    fun should_return_all_saved_medicine_when_getting_medicines() = runTest {
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

    @Test
    fun should_return_emptyList_when_getting_medicines_and_there_is_no_saved_medicine() = runTest {
        val allMedications = dao.getAllMedications()
        assertTrue(allMedications.isEmpty())
    }
}
