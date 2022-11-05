package com.example.mmoapplication.data.repository

import com.example.mmoapplication.data.model.MMOResponse
import com.example.mmoapplication.data.remote.service.MMOService
import com.example.mmoapplication.domain.repository.MMORepository

class MMORepositoryImpl(private val apiService: MMOService): MMORepository {
    override suspend fun getAllGames(): MMOResponse = apiService.getMMOGameList()
}