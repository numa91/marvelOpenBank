package com.numa91.marvelob.data.api

import com.numa91.marvelob.data.entity.BaseEntity
import com.numa91.marvelob.data.entity.CharacterEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {
    @GET("/v1/public/characters/{id}")
    suspend fun getCharacter(
        @Path("id") id: Long,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String
    ): BaseEntity

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String
    ): BaseEntity
}