package com.numa91.marvelob.domain.model


data class CharacterModel (
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: ImageModel
)