package com.krasouski.data.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import com.krasouski.domain.ColorRepository
import com.krasouski.domain.ColorType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "color_prefs")

class ColorRepositoryImpl(
    private val context: Context
) : ColorRepository {
    companion object {
        val COLOR_KEY: Preferences.Key<String> = stringPreferencesKey("selected_color")
    }

    override fun getSavedColor(): Flow<ColorType> =
        context.dataStore.data.map { prefs ->
            ColorType.fromString(prefs[COLOR_KEY] ?: ColorType.NONE.name)
        }

    override suspend fun saveColor(color: ColorType) {
        context.dataStore.edit { prefs ->
            prefs[COLOR_KEY] = color.name
        }
    }
}