package com.utad.tema17

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utad.tema17.databinding.ActivityMoviesListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListActivity : AppCompatActivity() {
    val TAG = "MoviesListActivity"
    private lateinit var binding: ActivityMoviesListBinding
    private lateinit var rvMovies: RecyclerView
    private var adapterMovies: MoviesAdapter? = null
    var dataMovies: ArrayList<MovieResponse.Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.arrowImageView.setOnClickListener {
            finish()
        }
        rvMovies = binding.listRecyclerView
        val mLayoutManager = GridLayoutManager(this, 2)
        rvMovies.layoutManager = mLayoutManager
        adapterMovies = MoviesAdapter(dataMovies, object : MovieClickedListener {
            override fun onMovieClicked(movie: MovieResponse.Movie) {
                //Toast.makeText(this@MoviesListActivity, movie.title, Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MoviesListActivity, DetailsMovieActivity::class.java)
                intent.putExtra("movie", movie)
                startActivity(intent)

            }
        })
        rvMovies.adapter = adapterMovies
        ApiRest.initService()

        val intent = intent
        val genre = intent.getParcelableExtra<GenresResponse.Genre>("genre")
        if (genre != null) {
            binding.titleGenreTextView.text = genre.name
            getMovies(genre.id.toString())
        }
    }

    private fun getMovies(genre: String) {
        val call = ApiRest.service.getMovies(genre)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    dataMovies.clear()
                    dataMovies.addAll(body.results)
                    Log.d(TAG, dataMovies.toString())
                    adapterMovies?.notifyDataSetChanged()
                } else {
                    Log.e(TAG, response.errorBody()?.string()?.let { Log.e(TAG, it) }.toString())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}