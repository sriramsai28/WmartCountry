package com.sriram.countries.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sriram.countries.domain.model.CountryListItem
import com.sriram.countries.domain.usecase.CountriesListUseCaseImpl
import com.sriram.countries.domain.usecase.CountriesListUseCaseResult
import com.sriram.countries.domain.usecase.CountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CountriesListViewModel(
    private val countriesUseCase: CountriesUseCase = CountriesListUseCaseImpl(),
) : ViewModel() {
    private val _result = MutableStateFlow<CountriesListResult>(CountriesListResult.Loading)
    val result = _result

    init {
        fetchCountriesList()
    }

    fun fetchCountriesList() {
        viewModelScope.launch {
            countriesUseCase.getCountriesList().let { countriesListUseCaseResult ->
                when (countriesListUseCaseResult) {
                    is CountriesListUseCaseResult.Error -> sendUpdate(
                        CountriesListResult.Error(
                            errorMessage = countriesListUseCaseResult.errorMessage,
                        ),
                    )
                    is CountriesListUseCaseResult.Success -> sendUpdate(
                        CountriesListResult.Success(
                            list = countriesListUseCaseResult.countryList,
                        ),
                    )
                }
            }
        }
    }

    private suspend fun sendUpdate(countriesListResult: CountriesListResult) {
        _result.emit(countriesListResult)
    }
}

sealed interface CountriesListResult {
    object Loading : CountriesListResult
    data class Success(val list: List<CountryListItem>) : CountriesListResult
    data class Error(val errorMessage: String) : CountriesListResult
}
