package com.numa91.marvelob.data.entity

import com.numa91.marvelob.data.mapper.Mapper
import com.numa91.marvelob.domain.model.BaseModel

data class BaseEntity(
    val code: Any,
    val status: String,
    val message: String,
    val data: CharacterDataEntity
) : Mapper<BaseModel> {

    override suspend fun map(): BaseModel = BaseModel(
        code = this.code,
        status = this.status,
        message = this.message,
        data = this.data.map()
    )

}

