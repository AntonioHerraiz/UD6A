package com.example.tema18.genres

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tema18.data.models.GenresResponse
import com.example.tema18.data.remote.ApiRest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GenresViewModel : ViewModel() {
    val genresList = MutableStateFlow(listOf<GenresResponse.Genre>())
    val loading = MutableStateFlow(false)

    fun getGenres() {
        loading.value = true

        viewModelScope.launch {
            val response = ApiRest.service.getGenres()
            if (response.isSuccessful && response.body() != null) {
                genresList.value = response.body()?.genres!!
            } else {
                Log.i("GenresViewModel", "getGenres: ${response.errorBody()?.string()}")
            }

            loading.value = false

        }

    }
}
