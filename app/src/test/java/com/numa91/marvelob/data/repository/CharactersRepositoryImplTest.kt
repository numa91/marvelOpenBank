package com.numa91.marvelob.data.repository

import com.numa91.marvelob.domain.model.BaseModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class CharactersRepositoryImplTest{
    @MockK
    lateinit var charactersRepositoryImpl: CharactersRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun `get characters data should return model`() = runBlocking {
        val characters = mockk<BaseModel>()
        every { runBlocking { charactersRepositoryImpl.getCharacters(10,10) } } returns (characters)

        val result = charactersRepositoryImpl.getCharacters(10,10)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$characters] must be matches on each other!",
            result,
            CoreMatchers.`is`(characters)
        )
    }

    @Test
    fun `get character data should return model`() = runBlocking {
        val character = mockk<BaseModel>()
        every { runBlocking { charactersRepositoryImpl.getCharacter(10) } } returns (character)

        val result = charactersRepositoryImpl.getCharacter(10)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$character] must be matches on each other!",
            result,
            CoreMatchers.`is`(character)
        )
    }
}