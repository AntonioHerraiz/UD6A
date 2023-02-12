package com.utad.tema17

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieGenreAdapter(private val data: ArrayList<String>) :
    RecyclerView.Adapter<MovieGenreAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView

        init {
            titleTextView = view.findViewById(R.id.tag)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieGenreAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_tag, parent, false)
        return MovieGenreAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieGenreAdapter.ViewHolder, position: Int) {
        val genre = data[position]
        holder.titleTextView.text = genre
    }

    override fun getItemCount(): Int = data.size
}