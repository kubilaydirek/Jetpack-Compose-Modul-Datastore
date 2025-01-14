package com.example.jetpack_compose_modul_datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.local_preference.UserPreference
import com.example.local_preference.data.KEYS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStore: UserPreference) : ViewModel() {
    private val _name = MutableStateFlow<String>("")
    val name = _name.asStateFlow()

    fun saveNameDatastore(name: String) {
        viewModelScope.launch {
            dataStore.saveData(KEYS.NAME, name)
        }
    }

    fun getData() {
        viewModelScope.launch {
            dataStore.getData<String>(KEYS.NAME).collect {
                _name.value = it ?: ""
            }
        }
    }


}