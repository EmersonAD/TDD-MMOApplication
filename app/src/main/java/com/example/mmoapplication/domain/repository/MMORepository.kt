package com.example.mmoapplication.domain.repository

import com.example.mmoapplication.data.model.MMOResponse
import retrofit2.Response

interface MMORepository {
    suspend fun getAllGames(): Response<MMOResponse>
}