package com.example.tema18.genres

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tema18.R
import kotlinx.coroutines.launch

class GenresFragment():Fragment(R.layout.fragment_genres) {

    private lateinit var pbLoading: View
    private lateinit var rvGeneros: RecyclerView
    private var adapter: GenresAdapter? = null

    private val viewModel: GenresViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvGeneros = view.findViewById(R.id.rvGenres)
        pbLoading = view.findViewById(R.id.progressbar)

        initList()
        listenEvents()
        viewModel.getGenres()
    }

    private fun initList() {
        val mLayoutManager = GridLayoutManager(context, 2)
        rvGeneros.layoutManager = mLayoutManager

        adapter = GenresAdapter()
        rvGeneros.adapter = adapter
    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.genresList.collect {
                        adapter?.data = it
                        adapter?.notifyDataSetChanged()
                    }
                }

                launch {
                    viewModel.loading.collect {
                        pbLoading.isVisible = it
                    }
                }
            }
        }
    }

}