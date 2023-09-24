package com.sriram.countries.domain.usecase

import com.sriram.countries.data.repository.CountriesRepositoryImpl
import com.sriram.countries.data.repository.CountriesRepositoryResult
import com.sriram.countries.domain.CountriesRepository
import com.sriram.countries.domain.model.CountryListItem
import com.sriram.countries.util.toCountryListItem

class CountriesListUseCaseImpl(
    private val countriesRepository: CountriesRepository = CountriesRepositoryImpl(),
) : CountriesUseCase {
    override suspend fun getCountriesList(): CountriesListUseCaseResult {
        countriesRepository.getCountries().let { countriesRepositoryResult ->
            when (countriesRepositoryResult) {
                is CountriesRepositoryResult.Failure -> return CountriesListUseCaseResult.Error(
                    errorMessage = countriesRepositoryResult.errorMessage,
                )
                is CountriesRepositoryResult.Success -> return CountriesListUseCaseResult.Success(
                    countryList = countriesRepositoryResult.countryList.map {
                        it.toCountryListItem()
                    },
                )
            }
        }
    }
}

sealed interface CountriesListUseCaseResult {
    data class Success(val countryList: List<CountryListItem>) : CountriesListUseCaseResult
    data class Error(val errorMessage: String) : CountriesListUseCaseResult
}
