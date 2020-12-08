package com.numa91.marvelob.data.enitity

import com.numa91.marvelob.data.entity.ImageEntity
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

class ImageEntityTest {

    @MockK
    lateinit var imageEntity: ImageEntity

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun  `map ImageEntity to Model`() = runBlocking {
        val imageModel = mockk<ImageModel>()
        every { runBlocking { imageEntity.map() } } returns (imageModel)

        val result = imageEntity.map()

        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$imageModel] must be matches on each other!",
            result,
            CoreMatchers.`is`(imageModel)
        )
    }
}