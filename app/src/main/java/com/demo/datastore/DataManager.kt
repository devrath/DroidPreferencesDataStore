package com.demo.datastore

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.demo.datastore.Constants.KEY_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataManager(context: Context) {

    // Create the dataStore
    private val dataStore = context.createDataStore(name = context.resources.getString(R.string.app_name))

    // Create some keys
    companion object {
        val USER_NAME_KEY = preferencesKey<String>(KEY_NAME)
    }

    // Store user data
    suspend fun storeUser(name: String) {
        dataStore.edit {
            it[USER_NAME_KEY] = name
        }
    }

    // Create a name flow
    val userNameFlow: Flow<String> = dataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }

}