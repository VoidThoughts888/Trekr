package com.truckbase.trekr.data.local_storage

import android.content.SharedPreferences
import javax.inject.Inject

class TokenManager @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun deleteToken() {
        val editor = sharedPreferences.edit()
        editor.putString("token", null)
        editor.apply()
    }

    fun saveUserDetails(name: String, emailAddress: String) {
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("newName", name)
            putString("newEmail", emailAddress)
            apply()
        }
    }
}