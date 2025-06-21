package com.corsolp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.corsolp.ui.R
import com.corsolp.ui.databinding.FragmentHomeBinding
import com.corsolp.ui.search.SearchFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var cityAdapter: CityWeatherAdapter

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityAdapter = CityWeatherAdapter { cityToDelete ->
            viewModel.removeFavoriteCity(cityToDelete.name)
        }

        binding.recyclerViewCities.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cityAdapter
        }

        binding.btnSearchCity.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.home_container, SearchFragment())
                .addToBackStack(null)
                .commit()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.favoritesList.collect { cityWeatherList ->
                cityAdapter.submitList(cityWeatherList)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}