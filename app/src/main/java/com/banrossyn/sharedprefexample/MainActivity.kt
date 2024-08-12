package com.banrossyn.sharedprefexample

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), OnSharedPreferenceChangeListener {

    override fun onDestroy() {
        SharedPref.unregisterOnSharedPreferenceChangeListener(this)
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        SharedPref.init(this.applicationContext)


        if (!SharedPref.isInitialized()) {
            SharedPref.init(applicationContext)
        }

        SharedPref.registerOnSharedPreferenceChangeListener(this)



        SharedPref.isLoggedIn = true
        SharedPref.userAge = 25
        SharedPref.userName = "Rossyn"


        val isLoggedIn = SharedPref.isLoggedIn
        val userAge = SharedPref.userAge
        val userName = SharedPref.userName


    }

    override fun onSharedPreferenceChanged(sharedPreferences : SharedPreferences?, key : String?) {
        when (key) {
            SharedPref.isLogged -> {

            }

            SharedPref.age -> {

            }
        }
    }
}