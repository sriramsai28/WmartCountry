package com.sriram.countries.data.model

data class CountriesDTO(
    val capital: String,
    val code: String,
    val currency: Currency,
    val demonym: String,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String,
)
