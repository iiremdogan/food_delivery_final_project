package com.iremdogan.fooddeliveryproject.model.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPrefManager (context: Context) {
    companion object {
        const val TOKEN = "com.iremdogan.fooddeliveryproject.TOKEN"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, "")
    }

    fun setOnboardingSeen() {
        sharedPreferences.edit().putBoolean("ONBOARDING", true).apply()
    }

    fun isOnboardingSeen(): Boolean {
        return sharedPreferences.getBoolean("ONBOARDING", false)
    }
}