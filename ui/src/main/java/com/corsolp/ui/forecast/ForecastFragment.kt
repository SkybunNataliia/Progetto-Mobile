package com.corsolp.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.corsolp.ui.databinding.FragmentForecastBinding
import com.corsolp.ui.home.HomeViewModel
import com.corsolp.ui.home.HomeViewModelFactory

class ForecastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ForecastViewModel by viewModels {
        ForecastViewModelFactory()
    }

    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityName = arguments?.getString("cityName") ?: "Rome"
        binding.titleCity.text = cityName

        forecastAdapter = ForecastAdapter()

        binding.recyclerViewCities.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCities.adapter = forecastAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.forecast.collect { forecastList ->
                forecastAdapter.submitList(forecastList)
            }
        }

        viewModel.loadForecast(cityName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}