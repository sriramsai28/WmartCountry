package com.sriram.countries.data.api

import com.sriram.countries.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofitBuilder = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        if (BuildConfig.DEBUG) {
            retrofitBuilder.client(client)
        }
        retrofitBuilder.build()
    }

    val countriesService by lazy { retrofit.create(CountriesAPI::class.java) }
}
