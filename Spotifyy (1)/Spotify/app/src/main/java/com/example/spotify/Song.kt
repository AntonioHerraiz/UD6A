package com.example.spotify

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(
    val titulo: String,
    val cantante: String,
    val imagen: String,
    var fav: Boolean
): Parcelable {
    constructor() : this("", "", "", false)
}