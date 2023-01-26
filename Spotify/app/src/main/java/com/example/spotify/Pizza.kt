package com.example.spotify

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pizza(
    val nombre: String,
    val ingredientes: MutableList<String>,
    val imagen: String,
    val precio: Double
): Parcelable {
    constructor() : this("", mutableListOf(), "", 0.0)
}