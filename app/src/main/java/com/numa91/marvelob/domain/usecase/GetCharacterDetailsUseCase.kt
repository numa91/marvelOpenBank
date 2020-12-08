package com.numa91.marvelob.domain.usecase

import com.numa91.marvelob.domain.model.BaseModel
import com.numa91.marvelob.domain.repository.CharactersRepository
import com.numa91.marvelob.domain.usecase.base.UseCase

class GetCharacterDetailsUseCase constructor(
    private val charactersRepository: CharactersRepository
) : UseCase<BaseModel, GetCharacterDetailsUseCase.Params?>() {

    override suspend fun run(params: Params?): BaseModel {
        return charactersRepository.getCharacter(id = params?.id ?: 0)
    }

    data class Params(val id: Long)

}