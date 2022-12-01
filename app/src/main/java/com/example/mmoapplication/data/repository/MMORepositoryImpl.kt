package com.example.mmoapplication.data.repository

import com.example.mmoapplication.data.model.MMOResponse
import com.example.mmoapplication.data.remote.service.MMOService
import com.example.mmoapplication.domain.repository.MMORepository
import retrofit2.Response

class MMORepositoryImpl(private val apiService: MMOService) : MMORepository {
    override suspend fun getAllGames(): Response<MMOResponse> {
        return apiService.getMMOGameList()
    }
}