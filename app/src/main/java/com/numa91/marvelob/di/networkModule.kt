package com.numa91.marvelob.di

import com.numa91.marvelob.BuildConfig
import com.numa91.marvelob.data.api.CharactersApi
import com.numa91.marvelob.data.repository.CharactersRepositoryImpl
import com.numa91.marvelob.domain.repository.CharactersRepository
import com.numa91.marvelob.domain.usecase.GetCharacterDetailsUseCase
import com.numa91.marvelob.domain.usecase.GetCharactersUseCase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val networkModule = module {

    single { createService(get()) }
    single { createRetrofit(get(), BuildConfig.API_BASE_URL) }
    single { createOkHttpClient() }
    single { MoshiConverterFactory.create() }
    single { Moshi.Builder().build() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}


fun createService(retrofit: Retrofit): CharactersApi {
    return retrofit.create(CharactersApi::class.java)

}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build())).build()
}

fun createCharactersRepository(charactersApi: CharactersApi): CharactersRepository {
    return CharactersRepositoryImpl(charactersApi)
}

fun createGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersUseCase{
    return GetCharactersUseCase(charactersRepository)
}

fun createGetCharacterDetailsUseCase(charactersRepository: CharactersRepository): GetCharacterDetailsUseCase{
    return GetCharacterDetailsUseCase(charactersRepository)
}