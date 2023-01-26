package com.example.tema14

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val DB_NAME = "MiMejorApp"
        val sharedPref: SharedPreferences = getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);

        val USERNAME = "username"

       /* val edit = sharedPref.edit()
        edit.putString(USERNAME, "Charles")
        edit.apply()

        */

        val user = sharedPref.getString(USERNAME, null)
        user?.let{ Log.i("MainActivity", it)}
    }
}