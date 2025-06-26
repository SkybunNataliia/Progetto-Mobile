package com.corsolp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.corsolp.domain.models.City
import com.corsolp.ui.R
import com.corsolp.ui.databinding.ItemCityBinding

class CityWeatherAdapter(
    private val onDeleteClicked: (City) -> Unit,
    private val onItemClicked: (City) -> Unit
) : ListAdapter<HomeViewModel.CityWithWeather, CityWeatherAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(
        private val binding: ItemCityBinding,
        private val onDeleteClicked: (City) -> Unit,
        private val onItemClicked: (City) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HomeViewModel.CityWithWeather) {
            binding.tvCityName.text = item.city.name
            binding.tvTemperature.text = "${item.weather.temperature.toInt()}°"

            val iconRes = when (item.weather.name.lowercase()) {
                "clear" -> R.drawable.clear
                "clouds" -> R.drawable.clouds
                "rain"-> R.drawable.rain
                "snow" -> R.drawable.snow
                "fog" -> R.drawable.fog
                "drizzle" -> R.drawable.drizzle
                "tornado" -> R.drawable.tornado
                "thunderstorm" -> R.drawable.thunderstorm
                else -> R.drawable.icon_default
            }

            binding.ivWeatherIcon.setImageResource(iconRes)

            binding.deleteButton.setOnClickListener {
                onDeleteClicked(item.city)
            }

            binding.root.setOnClickListener {
                onItemClicked(item.city)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onDeleteClicked, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<HomeViewModel.CityWithWeather>() {
        override fun areItemsTheSame(oldItem: HomeViewModel.CityWithWeather, newItem: HomeViewModel.CityWithWeather) =
            oldItem.city.name == newItem.city.name

        override fun areContentsTheSame(oldItem: HomeViewModel.CityWithWeather, newItem: HomeViewModel.CityWithWeather) =
            oldItem == newItem
    }
}