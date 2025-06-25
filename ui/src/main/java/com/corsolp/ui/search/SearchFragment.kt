package com.corsolp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.corsolp.ui.R
import com.corsolp.ui.databinding.FragmentSearchBinding
import com.corsolp.ui.forecast.ForecastFragment
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnSearch.setOnClickListener {
            val cityName = binding.etCityName.text.toString()
            if (cityName.isNotBlank()) {
                viewModel.searchCity(cityName)
            } else {
                Toast.makeText(requireContext(), "Enter name of city", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAddFavorite.setOnClickListener {
            viewModel.addToFavorites()
        }

        binding.btnDetails.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, ForecastFragment())
                .addToBackStack(null)
                .commit()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.weather
                .combine(viewModel.lastCitySearched) { weather, name -> Pair(weather, name) }
                .collect { (weather, name) ->
                    if (weather?.weather != null) {
                        binding.tvCityResult.text = name ?: "N/D"
                        binding.tvTempResult.text = "${weather.weather.temperature.toInt()}°"
                        binding.tvDescResult.text = weather.weather.description
                        binding.resultContainer.visibility = View.VISIBLE
                    } else {
                        binding.resultContainer.visibility = View.GONE
                    }
                }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}