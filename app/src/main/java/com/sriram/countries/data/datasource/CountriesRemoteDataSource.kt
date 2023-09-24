package com.sriram.countries.data.datasource

import com.sriram.countries.data.model.CountriesDTO

interface CountriesRemoteDataSource {

    suspend fun getCountriesFromAPI(): List<CountriesDTO>
}
