package com.example.librarytrackerapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.librarytrackerapp.data.network.auth.AuthClient
import com.example.librarytrackerapp.data.network.auth.AuthService
import com.example.librarytrackerapp.data.network.book.BookTrackerClient
import com.example.librarytrackerapp.data.network.book.BookTrackerService
import com.example.librarytrackerapp.data.repository.AuthRepositoryImpl
import com.example.librarytrackerapp.data.repository.BookTrackerRepositoryImpl
import com.example.librarytrackerapp.domain.repository.AuthRepository
import com.example.librarytrackerapp.domain.repository.BookTrackerRepository
import com.example.librarytrackerapp.util.NetworkConstants
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
class ProviderModule {
    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
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
    fun providesAuthClient(retrofit: Retrofit) : AuthClient {
        return retrofit.create(AuthClient::class.java)
    }

    @Singleton
    @Provides
    fun providesBookTrackerRepository(bookTrackerService: BookTrackerService): BookTrackerRepository {
        return BookTrackerRepositoryImpl(bookTrackerService)
    }

    @Singleton
    @Provides
    fun providesAuthRepository(authService: AuthService, prefs: SharedPreferences) : AuthRepository {
        return AuthRepositoryImpl(authService, prefs)
    }

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(NetworkConstants.TOKEN_PREF_KEY, Context.MODE_PRIVATE)
    }
}