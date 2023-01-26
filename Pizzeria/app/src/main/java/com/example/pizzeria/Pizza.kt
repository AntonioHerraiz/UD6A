package com.example.pizzeria

import android.os.Parcelable


data class Pizza(
    val nombre: String,
    val ingredientes: MutableList<String>,
    val imagen: String,
    val precio: Double
)