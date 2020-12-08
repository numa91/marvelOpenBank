package com.numa91.marvelob.domain.usecase

import com.numa91.marvelob.domain.model.BaseModel
import com.numa91.marvelob.domain.repository.CharactersRepository
import com.numa91.marvelob.domain.usecase.base.UseCase

class GetCharactersUseCase constructor(
    private val charactersRepository: CharactersRepository
) : UseCase<BaseModel, Any?>() {
    override suspend fun run(params: Any?): BaseModel {
        return charactersRepository.getCharacters()
    }

}