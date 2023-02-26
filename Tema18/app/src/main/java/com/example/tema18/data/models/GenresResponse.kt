package com.example.tema18.data.models

data class GenresResponse(
    val genres: List<Genre>
) {
    data class Genre(
        val id: String,
        val name: String
    )
}