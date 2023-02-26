package com.example.ud6.weatherlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ud6.databinding.WeatherListItemBinding
import com.example.ud6.models.DailyItem
import com.example.ud6.models.WeatherModel
import com.example.ud6.weatherlist.WeatherListFragment
import com.squareup.picasso.Picasso
import java.util.Calendar

class WeatherListAdapter(private val weatherList: List<DailyItem>): RecyclerView.Adapter<WeatherListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        return WeatherListViewHolder(WeatherListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.bind(weatherList[position], position)
    }

    override fun getItemCount(): Int = weatherList.size
}

class WeatherListViewHolder(private val binding: WeatherListItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(dailyItem: DailyItem, position: Int) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, position+1)
        val dayNumber = calendar.get(Calendar.DAY_OF_MONTH)
        val monthString = WeatherListFragment.getMonthString(calendar.get(Calendar.MONTH))
        binding.dayTitle.text = "$dayNumber $monthString"
        binding.temp.text = "Temperatura: ${dailyItem.temp.dayTemp}º"
        binding.humidity.text = "Humedad: ${dailyItem.humidity}%"
        Picasso.get().load("http://openweathermap.org/img/wn/${dailyItem.weather[0].icon}@2x.png").into(binding.dayIcon)
    }
}