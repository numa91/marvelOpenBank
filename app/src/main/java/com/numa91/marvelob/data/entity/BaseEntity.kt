package com.numa91.marvelob.data.entity

import com.numa91.marvelob.data.mapper.Mapper
import com.numa91.marvelob.domain.model.BaseModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseEntity(
    val code: Int,
    val status: String,
    val data: CharacterDataEntity
) : Mapper<BaseModel> {

    override suspend fun map(): BaseModel = BaseModel(
        code = this.code,
        status = this.status,
        data = this.data.map()
    )

}

