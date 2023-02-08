package com.maverick.beerpunk.api

class Repository() {
    private val api = ApiClient().retrofitService
    suspend fun getAllBeers() = api.getBeers()
}