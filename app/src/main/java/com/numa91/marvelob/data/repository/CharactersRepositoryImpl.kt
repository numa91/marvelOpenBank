package com.numa91.marvelob.data.repository

import com.numa91.marvelob.BuildConfig
import com.numa91.marvelob.BuildConfig.API_PRIVATE_KEY
import com.numa91.marvelob.BuildConfig.API_PUBLIC_KEY
import com.numa91.marvelob.data.api.CharactersApi
import com.numa91.marvelob.data.entity.BaseEntity
import com.numa91.marvelob.data.entity.CharacterEntity
import com.numa91.marvelob.domain.model.BaseModel
import com.numa91.marvelob.domain.model.CharacterModel
import com.numa91.marvelob.domain.repository.CharactersRepository
import com.numa91.marvelob.extensions.toMD5

private const val FORMAT = "%s%s%s"

class CharactersRepositoryImpl(
    private val api: CharactersApi
) : CharactersRepository {

    override suspend fun getCharacter(id: Long): BaseModel {
        val timestamp = System.currentTimeMillis().toString()
        return api.getCharacter(
            id = id,
            apiKey = API_PUBLIC_KEY,
            hash = apiHash(timestamp),
            timestamp = timestamp
        ).map()
    }

    override suspend fun getCharacters(): BaseModel {
        val timestamp = System.currentTimeMillis().toString()
        return api.getCharacters(
            apiKey = API_PUBLIC_KEY,
            hash = apiHash(timestamp),
            timestamp = timestamp
        ).map()
    }

    private fun apiHash(timestamp: String) =
        FORMAT.format(timestamp, API_PRIVATE_KEY, API_PUBLIC_KEY).toMD5()
}