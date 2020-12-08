package com.numa91.marvelob.domain.usecase

import com.numa91.marvelob.domain.model.BaseModel
import com.numa91.marvelob.domain.repository.CharactersRepository
import com.numa91.marvelob.domain.usecase.base.UseCase

class GetCharactersUseCase constructor(
    private val charactersRepository: CharactersRepository
) : UseCase<BaseModel, GetCharactersUseCase.Params>() {

    override suspend fun run(params: Params): BaseModel {
        return charactersRepository.getCharacters(params.offset, params.limit)
    }

    data class Params(val offset: Int, val limit: Int)

}