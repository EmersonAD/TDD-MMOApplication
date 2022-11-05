package com.example.mmoapplication.domain.usecase.home

import com.example.mmoapplication.data.model.MMODomain
import com.example.mmoapplication.domain.mapper.TransformMMOResponseToDomain
import com.example.mmoapplication.domain.repository.MMORepository

class GetAllMMOGamesUseCaseImpl(private val repository: MMORepository) : GetAllMMOGamesUseCase {
    override suspend fun invoke(): List<MMODomain> {
        return if (repository.getAllGames().isNotEmpty()) {
            TransformMMOResponseToDomain(repository.getAllGames())
        } else {
            emptyList()
            //TODO("MOCK OR CACHE IN FUTURE")
        }
    }
}