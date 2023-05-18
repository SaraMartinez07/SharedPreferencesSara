package com.example.sharedpreferences

import android.content.Context

object SharedPrefManager {
    private const val SHARED_PREF_NAME = "my_shared_pref"
    private const val KEY_NAME = "name"
    private const val KEY_CAREER = "career"
    private const val KEY_PHONE = "phone"

    fun saveData(context: Context, name: String, career: String, phone: String) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_CAREER, career)
        editor.putString(KEY_PHONE, phone)
        editor.apply()
    }

    fun loadData(context: Context): Triple<String?, String?, String?> {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val name = sharedPreferences.getString(KEY_NAME, null)
        val career = sharedPreferences.getString(KEY_CAREER, null)
        val phone = sharedPreferences.getString(KEY_PHONE, null)
        return Triple(name, career, phone)
    }
}
