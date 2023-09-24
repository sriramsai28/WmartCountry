package com.sriram.countries.util

import com.sriram.countries.data.model.CountriesDTO
import com.sriram.countries.domain.model.CountryListItem

fun CountriesDTO.toCountryListItem(): CountryListItem {
    return CountryListItem(
        capital = this.capital,
        code = this.code,
        name = this.name,
        region = this.region,
    )
}
