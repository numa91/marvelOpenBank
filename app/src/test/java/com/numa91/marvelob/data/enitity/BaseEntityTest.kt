package com.numa91.marvelob.data.enitity

import com.numa91.marvelob.data.entity.BaseEntity
import com.numa91.marvelob.data.entity.ImageEntity
import com.numa91.marvelob.domain.model.BaseModel
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

class BaseEntityTest {

    @MockK
    lateinit var baseEntity: BaseEntity

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun `map BaseEntity to Model` () = runBlocking {
        val baseModel = mockk<BaseModel>()
        every { runBlocking { baseEntity.map() } } returns (baseModel)

        val result = baseEntity.map()

        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$baseModel] must be matches on each other!",
            result,
            CoreMatchers.`is`(baseModel)
        )
    }
}