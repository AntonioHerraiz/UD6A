package com.utad.tema17

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var rvGeneros: RecyclerView
    val TAG = "MainActivity"
    private var adapterGenre: GenresAdapter? = null
    var dataGenre: ArrayList<GenresResponse.Genre> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGeneros = findViewById<RecyclerView>(R.id.rvGeneros)
        val mLayoutManager = GridLayoutManager(this, 2)
        rvGeneros.layoutManager = mLayoutManager

        adapterGenre = GenresAdapter(dataGenre, object : GenreClickedListener {
            override fun onGenreClicked(genre: GenresResponse.Genre) {
                //Toast.makeText(this@MainActivity, genre.name, Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, MoviesListActivity::class.java)
                intent.putExtra("genre", genre)
                startActivity(intent)
            }
        })
        rvGeneros.adapter = adapterGenre

        ApiRest.initService()
        getGenres()
    }

    private fun getGenres() {
        val call = ApiRest.service.getGenres()
        call.enqueue(object : Callback<GenresResponse> {
            override fun onResponse(
                call: Call<GenresResponse>,
                response: Response<GenresResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    dataGenre.clear()
                    dataGenre.addAll(body.genres)
                    Log.d(TAG, dataGenre.toString())
                    adapterGenre?.notifyDataSetChanged()

                } else {
                    Log.e(TAG, response.errorBody()?.string()?.let { Log.e(TAG, it) }.toString())
                }
            }

            override fun onFailure(call: Call<GenresResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}