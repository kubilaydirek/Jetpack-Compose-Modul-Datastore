package com.example.local_preference.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.local_preference.UserPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) : UserPreference {
    override fun <T : Any> getData(data: Preferences.Key<T>): Flow<T?> {
        return dataStore.data.catch { emit(emptyPreferences()) }
            .map { preference ->
                preference[data]
            }
    }

    override suspend fun <T : Any> saveData(data: Preferences.Key<T>, savedData: T) {
        dataStore.edit { preference ->
            preference[data] = savedData
        }
    }

}

object KEYS {
    val NAME = stringPreferencesKey("name")
}