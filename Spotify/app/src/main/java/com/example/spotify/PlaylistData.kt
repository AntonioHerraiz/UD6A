package com.example.spotify

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaylistData(
    val titulo: String,
    val seguidores : String,
    val imagen : String,
    val list : MutableList<Song>
): Parcelable {
    constructor() : this("", "", "", mutableListOf(Song()))
}
