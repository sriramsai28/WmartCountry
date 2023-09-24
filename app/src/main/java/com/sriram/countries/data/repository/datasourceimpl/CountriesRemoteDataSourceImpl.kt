package com.sriram.countries.data.repository.datasourceimpl

import com.sriram.countries.data.api.CountriesAPI
import com.sriram.countries.data.api.RetrofitInstance
import com.sriram.countries.data.datasource.CountriesRemoteDataSource
import com.sriram.countries.data.model.CountriesDTO

class CountriesRemoteDataSourceImpl(
    private val countriesAPI: CountriesAPI = RetrofitInstance.countriesService,
) : CountriesRemoteDataSource {
    override suspend fun getCountriesFromAPI(): List<CountriesDTO> {
        return countriesAPI.getCountries()
    }
}
