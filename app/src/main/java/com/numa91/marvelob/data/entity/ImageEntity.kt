package com.numa91.marvelob.data.entity

import com.numa91.marvelob.data.mapper.Mapper
import com.numa91.marvelob.domain.model.ImageModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageEntity(
    val path: String,
    val extension: String
) : Mapper<ImageModel> {

    override suspend fun map(): ImageModel = ImageModel(
        path = this.path,
        extension = this.extension
    )
}