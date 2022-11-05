package com.example.mmoapplication.domain.usecase.home

import com.example.mmoapplication.data.model.MMODomain

interface GetAllMMOGamesUseCase {
    suspend operator fun invoke(): List<MMODomain>
}