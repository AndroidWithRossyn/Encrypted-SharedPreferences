package com.banrossyn.sharedprefexample

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A singleton object for storing data locally using SharedPreferences.
 * It provides facilities to store various types of data (Boolean, Int, Long, Float, String, and String Set).
 */
object SharedPref {
    private var preferences: SharedPreferences? = null
    private const val SHARED_PREFERENCE_FILE_NAME = "MY-PREFERENCE"

    /**
     * Initializes the SharedPreferences with optional encryption.
     *
     * This method first attempts to initialize EncryptedSharedPreferences for securely storing
     * sensitive data. If an error occurs during encryption setup, it defaults to using
     * regular SharedPreferences to maintain functionality.
     *
     * @param context The application context used to initialize the SharedPreferences.
     *                This context is required to access the appropriate resources and files.
     *
     */
    fun init(context: Context) {
        try {
            // Create a MasterKey for EncryptedSharedPreferences using AES256_GCM encryption.
            val masterKeyAlias = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
            // Initialize EncryptedSharedPreferences with AES256 encryption for keys and values.
            this.preferences = EncryptedSharedPreferences.create(
                context,
                SHARED_PREFERENCE_FILE_NAME,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: Exception) {

            /*   Fallback to regular SharedPreferences in case of encryption failure.
             *    Crashing in Android Samsung Devices
             */
            this.preferences = context.getSharedPreferences(
                SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE
            )
            Log.e("SharedPref", "Exception: ${e.message}", e)
        }

    }

    /**
     * Checks the initialization status of SharedPreferences.
     * @return true if SharedPreferences has been initialized, false otherwise
     */
    fun isInitialized(): Boolean {
        return preferences != null
    }

    /**
     * Registers a listener to listen for changes in SharedPreferences.
     * @param listener An object implementing the OnSharedPreferenceChangeListener interface
     */
    fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        preferences?.registerOnSharedPreferenceChangeListener(listener)
    }

    /**
     * Unregisters a previously registered listener.
     * @param listener The OnSharedPreferenceChangeListener to be unregistered
     */
    fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        preferences?.unregisterOnSharedPreferenceChangeListener(listener)
    }


    const val isLogged = "isLoggedIn"
    const val age = "userAge"

    var isLoggedIn by BooleanPreference(isLogged, false)
    var userAge by IntPreference(age, 0)
    var userId by LongPreference("userId", 0L)
    var userHeight by FloatPreference("userHeight", 0f)
    var userName by StringPreference("userName", "")
    var fruits by StringSetPreference("fruits", setOf())
    var myObject by ObjectPreference("myKey", MyClass(), object : TypeToken<MyClass>() {})

    /**
     * Delegate for Boolean preferences.
     * @property name The key for the preference
     * @property defaultValue The default value if the preference is not set
     */
    private class BooleanPreference(private val name: String, private val defaultValue: Boolean) :
        ReadWriteProperty<SharedPref, Boolean> {
        override fun getValue(thisRef: SharedPref, property: KProperty<*>) =
            preferences?.getBoolean(name, defaultValue) ?: defaultValue

        override fun setValue(thisRef: SharedPref, property: KProperty<*>, value: Boolean) {
            preferences?.edit { putBoolean(name, value) }
        }
    }

    /**
     * Delegate for Int preferences.
     * @property name The key for the preference
     * @property defaultValue The default value if the preference is not set
     */
    private class IntPreference(private val name: String, private val defaultValue: Int) :
        ReadWriteProperty<SharedPref, Int> {
        override fun getValue(thisRef: SharedPref, property: KProperty<*>) =
            preferences?.getInt(name, defaultValue) ?: defaultValue

        override fun setValue(thisRef: SharedPref, property: KProperty<*>, value: Int) {
            preferences?.edit { putInt(name, value) }
        }
    }

    private class LongPreference(private val name: String, private val defaultValue: Long) :
        ReadWriteProperty<SharedPref, Long> {
        override fun getValue(thisRef: SharedPref, property: KProperty<*>) =
            preferences?.getLong(name, defaultValue) ?: defaultValue

        override fun setValue(thisRef: SharedPref, property: KProperty<*>, value: Long) {
            preferences?.edit { putLong(name, value) }
        }
    }

    private class FloatPreference(private val name: String, private val defaultValue: Float) :
        ReadWriteProperty<SharedPref, Float> {
        override fun getValue(thisRef: SharedPref, property: KProperty<*>) =
            preferences?.getFloat(name, defaultValue)?:defaultValue

        override fun setValue(thisRef: SharedPref, property: KProperty<*>, value: Float) {
            preferences?.edit { putFloat(name, value) }
        }
    }

    private class StringPreference(private val name: String, private val defaultValue: String?) :
        ReadWriteProperty<SharedPref, String?> {
        override fun getValue(thisRef: SharedPref, property: KProperty<*>) =
            preferences?.getString(name, defaultValue) ?: defaultValue

        override fun setValue(thisRef: SharedPref, property: KProperty<*>, value: String?) {
            preferences?.edit { putString(name, value) }
        }
    }

    private class StringSetPreference(
        private val name: String, private val defaultValue: Set<String>
    ) : ReadWriteProperty<SharedPref, Set<String>> {
        override fun getValue(thisRef: SharedPref, property: KProperty<*>) =
            preferences?.getStringSet(name, defaultValue) ?: defaultValue

        override fun setValue(thisRef: SharedPref, property: KProperty<*>, value: Set<String>) {
            preferences?.edit { putStringSet(name, value) }
        }
    }

    private class ObjectPreference<T : Any>(
        private val key: String,
        private val defaultValue: T,
        private val typeToken: TypeToken<T>
    ) : ReadWriteProperty<SharedPref, T> {

        @Throws(JsonParseException::class)
        override fun getValue(thisRef: SharedPref, property: KProperty<*>): T {
            if (key.isEmpty()) {
                Timber.w("> getObject, key must not be empty")
                return defaultValue
            }
            val json = preferences?.getString(key, null)
            return if (json == null) {
                Timber.i("> getObject, json is null for Key $key, returning defaultValue")
                defaultValue
            } else {
                try {
                    Gson().fromJson(json, typeToken.type) as T
                } catch (e: JsonSyntaxException) {
                    Timber.e("> getObject, Object stored with Key $key not able to instance to ${typeToken.type}")
                    throw e
                }
            }
        }

        @Throws(IllegalArgumentException::class)
        override fun setValue(thisRef: SharedPref, property: KProperty<*>, value: T) {
            require(key.isNotEmpty()) { "Key must not be empty" }
            Timber.i("> putObject, storing $value with key $key")
            preferences?.edit()?.putString(key, Gson().toJson(value))?.apply()
        }
    }
}