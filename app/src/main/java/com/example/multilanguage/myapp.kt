package com.example.multilanguage

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import java.util.Locale

class myapp: Application() {


    interface LanguageChangeListener {
        fun onLanguageChange()
    }

    class MyApplication : Application() {

        override fun onCreate() {
            super.onCreate()
            AppLanguageDelegate.initialize(this)
        }
    }

    object AppLanguageDelegate {
        private val observers = mutableListOf<LanguageChangeListener>()

        fun initialize(context: Context) {
            val currentLanguage = LanguageManager.getCurrentLanguage(context)
            setLocale(context, currentLanguage)
        }

        fun setLocale(context: Context, languageCode: String) {
            val locale = Locale(languageCode)
            Locale.setDefault(locale)
            val configuration = Configuration(context.resources.configuration)
            configuration.setLocale(locale)
            context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
            LanguageManager.setLanguage(context, languageCode)
            notifyLanguageChange()
        }

        fun addObserver(observer: MainActivity) {
            observers.add(observer)
        }

        fun removeObserver(observer: LanguageChangeListener) {
            observers.remove(observer)
        }

        private fun notifyLanguageChange() {
            for (observer in observers) {
                observer.onLanguageChange()
            }
        }
    }

    object LanguageManager {
        private const val LANGUAGE_PREF_KEY = "language"

        fun getCurrentLanguage(context: Context): String {
            val sharedPreferences = context.getSharedPreferences("LanguagePrefs", Context.MODE_PRIVATE)
            return sharedPreferences.getString(LANGUAGE_PREF_KEY, Locale.ENGLISH.language)
                ?: Locale.ENGLISH.language
        }

        fun setLanguage(context: Context, languageCode: String) {
            val sharedPreferences = context.getSharedPreferences("LanguagePrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putString(LANGUAGE_PREF_KEY, "hi").apply()
        }
    }

    }