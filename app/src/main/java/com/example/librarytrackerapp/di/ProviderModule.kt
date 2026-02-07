package com.example.librarytrackerapp.di

import com.example.librarytrackerapp.data.network.client.BookTrackerClient
import com.example.librarytrackerapp.data.network.service.BookTrackerService
import com.example.librarytrackerapp.data.repository.BookTrackerRepositoryImpl
import com.example.librarytrackerapp.domain.repository.BookTrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProviderModule {
    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesBookTrackerClient(retrofit: Retrofit) : BookTrackerClient {
        return retrofit.create(BookTrackerClient::class.java)
    }

    @Singleton
    @Provides
    fun providesBookTrackerRepository(bookTrackerService: BookTrackerService): BookTrackerRepository {
        return BookTrackerRepositoryImpl(bookTrackerService)
    }
}