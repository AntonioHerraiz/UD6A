package com.example.tema18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tema18.data.models.GenresResponse
import com.example.tema18.genres.GenresFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container,GenresFragment()).commit()
    }
}