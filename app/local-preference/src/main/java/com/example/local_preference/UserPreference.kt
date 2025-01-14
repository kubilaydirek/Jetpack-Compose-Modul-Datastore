package com.example.local_preference

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface UserPreference {
    fun <T : Any> getData(data: Preferences.Key<T>): Flow<T?>
    suspend fun <T : Any> saveData(data: Preferences.Key<T>, savedData: T)
}