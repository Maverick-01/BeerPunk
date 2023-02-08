package com.maverick.beerpunk.api

import com.maverick.beerpunk.model.BeerModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("beers")
    suspend fun getBeers(): Response<BeerModel>
}