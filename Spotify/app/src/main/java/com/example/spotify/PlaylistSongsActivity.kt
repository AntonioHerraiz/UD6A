package com.example.spotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spotify.databinding.ActivityMainBinding
import com.example.spotify.databinding.ActivityPlaylistSongsBinding
import com.example.spotify.databinding.FragmentSearchBinding

class PlaylistSongsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPlaylistSongsBinding
    private lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistSongsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()

    }

    private fun setup(){

        val myIntent = intent
        val playlist = myIntent.getParcelableExtra<PlaylistData>("playlist")
        binding.titlePlaylist.text = playlist?.titulo

        binding.imageView.setOnClickListener {
            finish()
        }

        recyclerView = binding.songsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        if (playlist != null) {
            recyclerView.adapter = SongAdapter(playlist.list)
        }
    }
}