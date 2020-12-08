package com.numa91.marvelob.domain.model

data class CharacterDataModel(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterModel>
)