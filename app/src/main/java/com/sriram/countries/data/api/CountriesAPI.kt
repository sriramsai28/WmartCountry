package com.sriram.countries.data.api

import com.sriram.countries.data.model.CountriesDTO
import retrofit2.http.GET

interface CountriesAPI {

    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): List<CountriesDTO>
}
