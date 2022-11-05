package com.example.mmoapplication.data.remote.service

import com.example.mmoapplication.data.model.MMOResponse
import retrofit2.http.GET

interface MMOService {
    @GET("/api1/games")
    suspend fun getMMOGameList(): MMOResponse
}