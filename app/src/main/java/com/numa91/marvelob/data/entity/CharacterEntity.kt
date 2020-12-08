package com.numa91.marvelob.data.entity

import com.numa91.marvelob.data.mapper.Mapper
import com.numa91.marvelob.domain.model.CharacterModel

data class CharacterEntity(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: ImageEntity
) : Mapper<CharacterModel> {

    override suspend fun map(): CharacterModel = CharacterModel(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnail = this.thumbnail.map()
    )
}

