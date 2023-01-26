package com.example.spotify

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class SongAdapter (private var dataSet: MutableList<Song>) :
    RecyclerView.Adapter<SongAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var titulo : TextView
        var cantantes : TextView
        var imagenCancion : ImageView
        var fav : ImageView
        val remove : ImageView
        val more : ImageView

        init {
            titulo = view.findViewById(R.id.titulo)
            cantantes = view.findViewById(R.id.cantantes)
            imagenCancion = view.findViewById(R.id.imagenCancion)
            fav = view.findViewById(R.id.favIcon)
            remove = view.findViewById(R.id.removeCircleIcon)
            more = view.findViewById(R.id.moreIcon)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.song, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val data = dataSet[position]
        viewHolder.titulo.setText(data.titulo)
        viewHolder.cantantes.setText("${data.cantante.toString()}")
        Picasso.get().load("${data.imagen}").into(viewHolder.imagenCancion)
        viewHolder.more.setImageResource(R.drawable.ic_baseline_more_vert_24)
        viewHolder.fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        viewHolder.remove.setImageResource(R.drawable.ic_outline_remove_circle_outline_24)
        viewHolder.fav.setOnClickListener() {
            if (!data.fav) {
                data.fav = true
                viewHolder.fav.setImageResource(R.drawable.ic_baseline_favorite_24)

            } else {
                data.fav = false
                viewHolder.fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
                true
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


}