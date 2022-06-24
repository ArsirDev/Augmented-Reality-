package com.example.animalaugmentedreality.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.animalaugmentedreality.utils.Content.DATASTORE_INSTALL
import com.example.animalaugmentedreality.utils.Content.DATASTORE_INSTALL_PREFERENCE
import kotlinx.coroutines.flow.first

class SessionManager(
    val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = DATASTORE_INSTALL
    )

    suspend fun setStatusFirstInstall(status: Boolean) {
        context.dataStore.edit { result ->
            result[FIRST_INSTALL] = status
        }
    }

    suspend fun getStatusFirstInstall(): Boolean {
        return try {
            val preferences = context.dataStore.data.first()
            preferences[FIRST_INSTALL] ?: true
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        val FIRST_INSTALL = booleanPreferencesKey(
            name = DATASTORE_INSTALL_PREFERENCE
        )
    }
}