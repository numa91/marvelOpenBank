package com.numa91.marvelob.data.enitity

import com.numa91.marvelob.data.entity.CharacterEntity
import com.numa91.marvelob.data.entity.ImageEntity
import com.numa91.marvelob.domain.model.CharacterModel
import com.numa91.marvelob.domain.model.ImageModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class CharacterEntityTest {

    @MockK
    lateinit var characterEntity: CharacterEntity

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun `map CharacterEntity to Model`() = runBlocking {
        val characterModel = mockk<CharacterModel>()
        every { runBlocking { characterEntity.map() } } returns (characterModel)

        val result = characterEntity.map()

        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$characterModel] must be matches on each other!",
            result,
            CoreMatchers.`is`(characterModel)
        )
    }
}