package com.oyelabs.marvel.universe.main.proto

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.StatePreferences
import javax.inject.Inject

class ProtoManager @Inject constructor (var context: Context){
    val dataStoreStatePreferences : DataStore<StatePreferences> = context.stateDataStore


    suspend fun setCurrentTheme(darkMode: String) {
        dataStoreStatePreferences.updateData { preferences ->
            preferences.toBuilder()
                .setCurrentTheme(darkMode)
                .build()
        }
    }


    suspend fun setDefaultValue(){
        dataStoreStatePreferences.updateData { preferences->
            preferences.toBuilder()
                .setCurrentTheme(context.getString(R.string.system_default_text))
                .build()
        }
    }



    companion object {
        private const val DATA_STORE_FILE_NAME = "state_prefs.pb"

        private val Context.stateDataStore: DataStore<StatePreferences> by dataStore(
            fileName = DATA_STORE_FILE_NAME,
            serializer = StatePreferencesSerializer
        )

    }

}





