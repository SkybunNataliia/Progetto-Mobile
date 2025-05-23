package com.corsolp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.corsolp.ui.R
import com.corsolp.ui.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityAdapter = CityAdapter(emptyList())
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
    }

    fun updateCities(newCities: List<City>) {
        cityAdapter.updateData(newCities)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}