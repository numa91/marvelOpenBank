package com.numa91.marvelob.data.mapper

interface Mapper<T> {
    suspend fun map(): T
}
