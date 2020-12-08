package com.numa91.marvelob.domain.model

data class BaseModel(
    val code: Any,
    val status: String,
    val message: String,
    val data: CharacterDataModel
)