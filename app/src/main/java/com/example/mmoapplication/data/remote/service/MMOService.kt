package com.example.mmoapplication.data.remote.service

import com.example.mmoapplication.data.model.MMOResponse
import com.example.mmoapplication.data.util.URL
import retrofit2.http.GET

interface MMOService {

    @GET(URL.END_POINT_GAMES)
    suspend fun getMMOGameList(): MMOResponse
}