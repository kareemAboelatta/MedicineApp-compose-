package com.example.composemed.di

import android.content.Context
import com.example.composemed.common.Constants
import com.example.composemed.data.remote.ApiHealthService
import com.example.composemed.data.repository.MedicationRepositoryImpl
import com.example.composemed.domain.repository.MedicationRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
        apiPlaceholderService: ApiHealthService
    ): MedicationRepository =
        MedicationRepositoryImpl(apiService = apiPlaceholderService)




}