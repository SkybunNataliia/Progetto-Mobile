package com.corsolp.data.local

import android.content.Context
import com.corsolp.domain.models.City
import kotlinx.coroutines.flow.Flow
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("favorites", Context.MODE_PRIVATE)

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val type = Types.newParameterizedType(Set::class.java, City::class.java)
    private val adapter = moshi.adapter<Set<City>>(type)

    val favoriteCities: Flow<Set<City>> = callbackFlow {

        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == "cities") {
                trySend(getFavorites())
            }
        }

        prefs.registerOnSharedPreferenceChangeListener(listener)
        trySend(getFavorites())

        awaitClose {
            prefs.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }

    fun addCity(city: City): Boolean {
        val current = getFavorites().toMutableSet()
        val added = current.add(city)
        if (added) {
            val json = adapter.toJson(current)
            prefs.edit().putString("cities", json).apply()
        }
        return added
    }

    fun removeCity(city: City): Boolean {
        val current = getFavorites().toMutableSet()
        val removed = current.remove(city)
        if (removed) {
            val json = adapter.toJson(current)
            prefs.edit().putString("cities", json).apply()
        }
        return removed
    }

    private fun getFavorites(): Set<City> {
        val json = prefs.getString("cities", null)
        return if (!json.isNullOrEmpty()) {
            adapter.fromJson(json) ?: emptySet()
        } else {
            emptySet()
        }
    }
}
