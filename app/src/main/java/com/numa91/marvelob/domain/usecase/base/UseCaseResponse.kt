package com.numa91.marvelob.domain.usecase.base


interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: String?)
}