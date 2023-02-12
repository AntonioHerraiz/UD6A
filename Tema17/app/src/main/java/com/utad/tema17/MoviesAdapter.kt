package com.utad.tema17

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

interface MovieClickedListener {
    fun onMovieClicked(movie: MovieResponse.Movie)
}

class MoviesAdapter(
    private val data: ArrayList<MovieResponse.Movie>,
    private val movieClickedListener: MovieClickedListener
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView
        val imageView: ImageView

        init {
            titleTextView = view.findViewById(R.id.titleItemMovie)
            imageView = view.findViewById(R.id.imageItemMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = data[position]
        holder.titleTextView.text = movie.title

        Picasso.get().load("${ApiRest.URL_IMAGES}${movie.poster_path}").into(holder.imageView)

        holder.itemView.setOnClickListener {
            Log.v("Pulso sobre", movie.title)
            movieClickedListener.onMovieClicked(movie)
        }
    }

    override fun getItemCount(): Int = data.size


}