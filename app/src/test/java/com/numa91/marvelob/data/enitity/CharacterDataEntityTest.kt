package com.numa91.marvelob.data.enitity

import com.numa91.marvelob.data.entity.CharacterDataEntity
import com.numa91.marvelob.data.entity.ImageEntity
import com.numa91.marvelob.domain.model.CharacterDataModel
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

class CharacterDataEntityTest {

    @MockK
    lateinit var characterDataEntity: CharacterDataEntity

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun `map CharacterDataEntity to Model`() = runBlocking {
        val characterDataModel = mockk<CharacterDataModel>()
        every { runBlocking { characterDataEntity.map() } } returns (characterDataModel)

        val result = characterDataEntity.map()

        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$characterDataModel] must be matches on each other!",
            result,
            CoreMatchers.`is`(characterDataModel)
        )
    }
}