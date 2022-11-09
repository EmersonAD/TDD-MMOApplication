package com.example.mmoapplication.domain.mapper

import com.example.mmoapplication.data.model.MMODomain
import com.example.mmoapplication.data.model.MMOResponseItem

object TransformMMOResponseToDomain {

    operator fun invoke(response: List<MMOResponseItem>): List<MMODomain> {
        return response.map { mmoGame ->
            MMODomain(
                title = mmoGame.title,
                genre = mmoGame.genre,
                platform = mmoGame.platform,
                shortDescription = mmoGame.shortDescription,
                thumbnail = mmoGame.thumbnail,
                website = mmoGame.gameUrl
            )
        }
    }
}