package com.example.mmoapplication.domain.usecase.home

import com.example.mmoapplication.data.model.MMODomain
import com.example.mmoapplication.domain.mapper.TransformMMOResponseToDomain
import com.example.mmoapplication.domain.repository.MMORepository

class GetAllMMOGamesUseCaseImpl(private val repository: MMORepository) : GetAllMMOGamesUseCase {
    override suspend fun getGames(): List<MMODomain> {
        repository.getAllGames().body()?.let { gamesList ->

            return when (repository.getAllGames().code()) {
                200 -> TransformMMOResponseToDomain(gamesList)
                204 -> throw Exception("No content")
                in 400..500 -> throw Exception()
                else -> throw Exception()
            }

        } ?: throw Exception("Empty body")
    }

}
