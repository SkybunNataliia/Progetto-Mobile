package com.corsolp.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.corsolp.domain.models.ForecastItem
import com.corsolp.ui.databinding.ItemForecastBinding
import com.corsolp.ui.R

class ForecastAdapter : ListAdapter<ForecastItem, ForecastAdapter.ForecastViewHolder>(
    ForecastDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ForecastViewHolder(
        private val binding: ItemForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ForecastItem) {
            binding.tvDate.text = item.dateTime
            binding.tvTemp.text = "${item.temperature.toInt()}°C"
            binding.tvDesc.text = item.description

            val iconRes = when (item.weather.lowercase()) {
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

            binding.forecastIcon.setImageResource(iconRes)
        }
    }
}

class ForecastDiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<ForecastItem>() {
    override fun areItemsTheSame(oldItem: ForecastItem, newItem: ForecastItem): Boolean {
        return oldItem.dateTime == newItem.dateTime
    }

    override fun areContentsTheSame(oldItem: ForecastItem, newItem: ForecastItem): Boolean {
        return oldItem == newItem
    }
}