package com.example.ud6.weatherlist

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ud6.databinding.FragmentWeatherListBinding
import com.example.ud6.weatherlist.adapter.WeatherListAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.time.format.DateTimeFormatter
import java.util.Calendar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherListFragment : Fragment() {

    private var binding: FragmentWeatherListBinding? = null
    private val viewModel: WeatherListViewModel by activityViewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherListBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        listenEvents()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
            return
        }
        getWeather()
    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.weatherModel.collect {
                        if (it != null) {
                            val dayNumber = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                            val monthString = getMonthString(Calendar.getInstance().get(Calendar.MONTH))
                            val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                            val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)
                            binding?.currentDate?.text = "$dayNumber $monthString, $currentHour:$currentMinute"
                            binding?.temperature?.text = "Temperatura: ${it.current.temp}º"
                            binding?.humidity?.text = "Humedad: ${it.current.humidity}%"
                            binding?.weatherList?.adapter = WeatherListAdapter(it.dailyList)
                            Picasso.get().load("http://openweathermap.org/img/wn/${it.current.weather[0].icon}@2x.png").into(binding?.currentIcon)
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getWeather() {
        binding?.progressLoader?.visibility = View.VISIBLE
        fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, object: CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
                return CancellationTokenSource().token
            }

            override fun isCancellationRequested(): Boolean {
                return false
            }

        })
            .addOnSuccessListener {
            if (it != null) {
                viewModel.getWeather(it.latitude.toString(), it.longitude.toString())
            } else {
                viewModel.getWeather()
            }
                binding?.progressLoader?.visibility = View.GONE
        }
            .addOnFailureListener {
                viewModel.getWeather()
                binding?.progressLoader?.visibility = View.GONE
            }
    }

    private fun requestLocationPermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    getWeather()
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    getWeather()
                } else -> {
                // No location access granted.
            }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    companion object {
        fun getMonthString(month: Int): String {
            return when (month) {
                0 -> "de Enero"
                1 -> "de Febrero"
                2 -> "de Marzo"
                3 -> "de Abril"
                4 -> "de Mayo"
                5 -> "de Junio"
                6 -> "de Julio"
                7 -> "de Agosto"
                8 -> "de Septiembre"
                9 -> "de Octubre"
                10 -> "de Noviembre"
                11 -> "de Diciembre"
                else -> ""
            }
        }
    }
}