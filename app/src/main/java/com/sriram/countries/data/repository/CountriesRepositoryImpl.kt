package com.sriram.countries.data.repository

import android.util.Log
import com.sriram.countries.data.datasource.CountriesRemoteDataSource
import com.sriram.countries.data.model.CountriesDTO
import com.sriram.countries.data.repository.datasourceimpl.CountriesRemoteDataSourceImpl
import com.sriram.countries.domain.CountriesRepository

class CountriesRepositoryImpl(
    private val remoteDataSource: CountriesRemoteDataSource = CountriesRemoteDataSourceImpl(),
) : CountriesRepository {
    override suspend fun getCountries(): CountriesRepositoryResult {
        return try {
            val result = remoteDataSource.getCountriesFromAPI()
            Log.d("CountriesRepositoryImpl", "Response --> $result")
            if (result.isNotEmpty()) {
                CountriesRepositoryResult.Success(result)
            } else {
                CountriesRepositoryResult.Failure("Something went wrong: Error Code 001")
            }
        } catch (e: Exception) {
            Log.e("CountriesRepositoryImpl", "ERROR --> ${e.message}")
            CountriesRepositoryResult.Failure("Something went wrong: Error Code 002")
        }
    }
}

sealed interface CountriesRepositoryResult {
    data class Success(val countryList: List<CountriesDTO>) : CountriesRepositoryResult
    data class Failure(val errorMessage: String) : CountriesRepositoryResult
}
