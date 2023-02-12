package com.utad.tema17

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*
import com.squareup.picasso.Picasso
import com.utad.tema17.databinding.ActivityDetailsMovieBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsMovieActivity : AppCompatActivity() {
    val TAG = "DetailsActivity"
    private lateinit var binding: ActivityDetailsMovieBinding
    private lateinit var rvGeneros: RecyclerView
    private var genreList: ArrayList<String> = ArrayList()
    var dataGenre: ArrayList<GenresResponse.Genre> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.arrowImageView.setOnClickListener {
            finish()
        }

        val intent = intent
        val movie = intent.getParcelableExtra<MovieResponse.Movie>("movie")
        if (movie != null) {
            Picasso.get().load("${ApiRest.URL_IMAGES}${movie.poster_path}")
                .into(binding.detailsImageView)

            binding.titleTextView.text = movie.title
            binding.descriptionTextView.text = movie.overview
            binding.titleMovieTextView.text = movie.title

            binding.dateTextView.text = "Año de estreno: ${movie.release_date.split("-")[0]}"
            rvGeneros = findViewById<RecyclerView>(R.id.genresMovieRecyclerView)
            val layoutManager = FlexboxLayoutManager(this)
            layoutManager.flexWrap = FlexWrap.WRAP
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.justifyContent = JustifyContent.FLEX_START
            layoutManager.alignItems = AlignItems.FLEX_START
            rvGeneros.layoutManager = layoutManager


            ApiRest.initService()
            val call = ApiRest.service.getGenres()
            call.enqueue(object : Callback<GenresResponse> {
                override fun onResponse(
                    call: Call<GenresResponse>, response: Response<GenresResponse>
                ) {
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        dataGenre.clear()
                        dataGenre.addAll(body.genres)

                        val genre_ids = movie.genre_ids.map { it.toInt() }
                        for (it in dataGenre) {
                            if (genre_ids.contains(it.id)) {
                                genreList.add(it.name)
                            }
                        }
                        Log.i(TAG, genreList.toString())
                        rvGeneros.adapter = MovieGenreAdapter(genreList)
                    } else {
                        Log.e(
                            TAG, response.errorBody()?.string()?.let { Log.e(TAG, it) }.toString()
                        )
                    }
                }

                override fun onFailure(call: Call<GenresResponse>, t: Throwable) {
                    Log.e(TAG, t.message.toString())
                }
            })
        }
    }
}