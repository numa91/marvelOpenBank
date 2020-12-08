package com.numa91.marvelob.domain.model

data class BaseModel(
    val code: Int,
    val status: String,
    val data: CharacterDataModel
)