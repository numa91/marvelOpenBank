package com.numa91.marvelob.di

import com.numa91.marvelob.features.characters.characterDetail.CharacterDetailViewModel
import com.numa91.marvelob.features.characters.characterList.CharacterListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module



val appModule = module {

    viewModel { CharacterListViewModel(get()) }

    viewModel { CharacterDetailViewModel(get()) }

    single { createGetCharactersUseCase(get()) }

    single { createGetCharacterDetailsUseCase(get()) }

    single { createCharactersRepository(get()) }

}