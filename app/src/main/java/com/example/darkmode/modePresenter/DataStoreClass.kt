package com.example.darkmode.modePresenter

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.createDataStore
import com.example.darkmode.util.UiMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreClass(context: Context) {
    companion object{
        private const val DATA_STORE_NAME = "settingMode.pref"
        private val IS_DARK = booleanPreferencesKey("is_dark")
    }

    private val appContext = context.applicationContext
    private val dataStore : DataStore<Preferences> = appContext.createDataStore(
        name = DATA_STORE_NAME
    )

    suspend fun setDarkMode(uiMode:UiMode){
        dataStore.edit {pre ->
            pre[IS_DARK] = when(uiMode){
                UiMode.LIGHT -> false
                UiMode.DARK -> true
            }
        }
    }

    val uiModeFlow: Flow<UiMode> = dataStore.data
        .catch {
            if (it is IOException){
                it.printStackTrace()
                emit(emptyPreferences())
            }else{
                throw it
            }
        }.map {pref ->
            when(pref[IS_DARK] ?: false){
                true -> UiMode.DARK
                false ->UiMode.LIGHT
            }
        }
}