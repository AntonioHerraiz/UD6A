package com.example.spotify


import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PopularListAdapter(private var dataSet: MutableList<PlaylistData>) :
    RecyclerView.Adapter<PopularListAdapter.ViewHolder>() {
    lateinit var playlistAction: (Int) -> Unit

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var title : TextView
        var seguidores : TextView
        var imageView : ImageView
        val imageViewIcono : ImageView

        init {
            title = view.findViewById(R.id.title)
            seguidores = view.findViewById(R.id.seguidores)
            imageView = view.findViewById(R.id.imagenPlaylist)
            imageViewIcono = view.findViewById(R.id.icono)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.grid_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val data = dataSet[position]
        viewHolder.title.setText(data.titulo)
        viewHolder.seguidores.setText("${data.seguidores.toString()}")
        Picasso.get().load("${data.imagen}").into(viewHolder.imageView)
        Picasso.get().load(R.drawable.reproducir).into(viewHolder.imageViewIcono)
        viewHolder.itemView.setOnClickListener() {
            playlistAction(position)
            true
        }
    }

    fun setAction (playlists: MutableList<PlaylistData>, action : (Int) -> Unit) {
        this.dataSet = playlists
        this.playlistAction = action
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


}