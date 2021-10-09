package com.shopshop

import android.app.Application
import android.content.Context

private const val KEY_REFERENCES = "shop_shop_pref"
private const val KEY_TOKEN = "shop_shop_token"

class App : Application() {

    companion object{
        private lateinit var instance: App


        private val preferences by lazy {
            instance.getSharedPreferences(KEY_REFERENCES, Context.MODE_PRIVATE)
        }

        fun saveToken(token: String){
            preferences.edit()
                .putString(KEY_TOKEN, token)
                .apply()
        }

        fun getToken():String?{
            return preferences.getString(KEY_TOKEN, null)
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}