package com.example.mmoapplication.domain.repository

import com.example.mmoapplication.data.model.MMOResponse

interface MMORepository {
    suspend fun getAllGames(): MMOResponse
}