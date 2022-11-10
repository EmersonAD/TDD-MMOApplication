package com.example.mmoapplication.domain.usecase.home

import com.example.mmoapplication.data.model.MMODomain
import com.example.mmoapplication.domain.mapper.TransformMMOResponseToDomain
import com.example.mmoapplication.domain.repository.MMORepository

class GetAllMMOGamesUseCaseImpl(private val repository: MMORepository) : GetAllMMOGamesUseCase {
    override suspend fun getGames(): List<MMODomain> {
        try {
            repository.getAllGames().let { listOfGames ->
                return TransformMMOResponseToDomain.init(listOfGames)
            }
        } catch (e: Exception) {
            throw IllegalArgumentException("Api response on failure")
        }
    }
}