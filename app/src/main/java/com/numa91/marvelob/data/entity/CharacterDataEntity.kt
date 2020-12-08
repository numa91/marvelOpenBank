package com.numa91.marvelob.data.entity

import com.numa91.marvelob.data.mapper.Mapper
import com.numa91.marvelob.domain.model.CharacterDataModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataEntity(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterEntity>
) : Mapper<CharacterDataModel> {

    override suspend fun map(): CharacterDataModel = CharacterDataModel(
        offset = this.offset,
        limit = this.limit,
        total = this.total,
        count = this.count,
        results = this.results.map { it.map() }
    )
}

