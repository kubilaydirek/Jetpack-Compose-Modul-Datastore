package com.example.local_preference.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.local_preference.UserPreference
import com.example.local_preference.data.UserDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserPreferenceModule {

    @Singleton
    @Provides
    fun provideUserPreferenceModule(dataStore: DataStore<Preferences>): UserPreference {
        return UserDataStore(dataStore)
    }
}