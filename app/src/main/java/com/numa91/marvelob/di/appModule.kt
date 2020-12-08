package com.numa91.marvelob.di

import org.koin.dsl.module



val appModule = module {

    single { createGetCharactersUseCase(get()) }

    single { createCharactersRepository(get()) }

}