package com.sriram.countries.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sriram.countries.R
import com.sriram.countries.databinding.CountriesListFragmentBinding
import com.sriram.countries.ui.fragments.datpter.CountryListAdapter
import com.sriram.countries.ui.viewmodel.CountriesListResult
import com.sriram.countries.ui.viewmodel.CountriesListViewModel
import kotlinx.coroutines.launch

class CountriesListFragment : Fragment(R.layout.countries_list_fragment) {
    private lateinit var binding: CountriesListFragmentBinding
    private lateinit var countryListAdapter: CountryListAdapter
    private val viewModel: CountriesListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CountriesListFragmentBinding.bind(view)
        observe()
        setAdapter()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.result.collect { result ->
                when (result) {
                    is CountriesListResult.Error -> {
                        with(binding) {
                            errorMessage.isVisible = true
                            errorMessage.text =
                                resources.getString(
                                    R.string.error,
                                    result.errorMessage,
                                )
                            errorMessage.setOnClickListener {
                                viewModel.fetchCountriesList()
                            }
                            loadigView.root.isVisible = false
                            countryList.isGone = true
                        }
                    }
                    CountriesListResult.Loading -> {
                        with(binding) {
                            loadigView.root.isVisible = true
                        }
                    }
                    is CountriesListResult.Success -> {
                        with(binding) {
                            loadigView.root.isGone = true
                            errorMessage.isGone = true
                            countryList.isVisible = true
                        }
                        countryListAdapter.setCountries(result.list)
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        countryListAdapter = CountryListAdapter()
        with(binding) {
            countryList.adapter = countryListAdapter
            countryList.layoutManager = LinearLayoutManager(this@CountriesListFragment.context)
        }
    }
}
