package com.utad.tema17

import android.os.Parcelable

import kotlinx.parcelize.Parcelize

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
) {
    @Parcelize
    data class Movie(
        val adult: Boolean,
        val backdrop_path: String,
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int,
        val genre_ids: ArrayList<String>
    ) : Parcelable
}
