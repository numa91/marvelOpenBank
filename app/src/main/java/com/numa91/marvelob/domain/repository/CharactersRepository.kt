package com.numa91.marvelob.domain.repository

import com.numa91.marvelob.data.entity.BaseEntity
import com.numa91.marvelob.domain.model.BaseModel
import com.numa91.marvelob.domain.model.CharacterModel

interface CharactersRepository {
    suspend fun getCharacter (id: Long) : BaseModel
    suspend fun getCharacters () : BaseModel

}