package com.sriram.countries.domain.usecase

interface CountriesUseCase {
    suspend fun getCountriesList(): CountriesListUseCaseResult
}
