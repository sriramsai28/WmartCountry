package com.sriram.countries.domain

import com.sriram.countries.data.repository.CountriesRepositoryResult

interface CountriesRepository {
    suspend fun getCountries(): CountriesRepositoryResult
}
