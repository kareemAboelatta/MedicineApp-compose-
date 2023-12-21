package com.example.composemed.common.di

import android.content.Context
import androidx.room.Room
import com.example.composemed.common.Constants
import com.example.composemed.home.data.local.AppDatabase
import com.example.composemed.home.data.local.MedicationDao
import com.example.composemed.home.data.remote.ApiHealthService
import com.example.composemed.home.data.repository.LocalMedicationRepositoryImp
import com.example.composemed.home.data.repository.MedicationRepositoryImpl
import com.example.composemed.home.domain.repository.LocalMedicationRepository
import com.example.composemed.home.domain.repository.MedicationRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object
AppModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context



    //remote
    @Singleton
    @Provides
    fun provideApiHealthService(): ApiHealthService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ApiHealthService::class.java)
    }

    @Singleton
    @Provides
    fun provideMedicationRepository(
        apiPlaceholderService: ApiHealthService,
        ioDispatcher: CoroutineDispatcher

    ): MedicationRepository =
        MedicationRepositoryImpl(
            apiService = apiPlaceholderService,
            ioDispatcher = ioDispatcher
        )



    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    //local
    @Singleton
    @Provides
    fun provideLocalMedicationRepository(
        medicationDao: MedicationDao,
        ioDispatcher: CoroutineDispatcher
    ): LocalMedicationRepository =
        LocalMedicationRepositoryImp(
            medicationDao = medicationDao,
            ioDispatcher = ioDispatcher
        )

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "medication_database"
        ).build()
    }

    @Provides
    fun provideMedicationDao(database: AppDatabase): MedicationDao {
        return database.medicationDao()
    }





}