package com.maverick.beerpunk.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(ApiUtility.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        // we need to add converter factory to
        // convert JSON object to Java object
        .build()
    val retrofitService: ApiInterface = retrofit.create(ApiInterface::class.java)
}