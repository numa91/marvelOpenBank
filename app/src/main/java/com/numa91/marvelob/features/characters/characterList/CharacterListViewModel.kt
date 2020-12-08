package com.numa91.marvelob.features.characters.characterList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numa91.marvelob.domain.model.BaseModel
import com.numa91.marvelob.domain.usecase.GetCharactersUseCase
import com.numa91.marvelob.domain.usecase.base.UseCaseResponse
import kotlinx.coroutines.cancel

class CharacterListViewModel constructor(private val getCharactersUseCase: GetCharactersUseCase) : ViewModel() {

    val charactersData = MutableLiveData<BaseModel>()
    val showProgressbar = MutableLiveData<Boolean>()
    val errorMsg = MutableLiveData<String>()

    fun getCharacters(){
        showProgressbar.value = true
        getCharactersUseCase.invoke(viewModelScope,null, object :UseCaseResponse<BaseModel>{
            override fun onSuccess(result: BaseModel) {
                charactersData.value = result
                showProgressbar.value = false
            }

            override fun onError(apiError: String?) {
                errorMsg.value = if(apiError.isNullOrEmpty()) "ERROR" else apiError
                showProgressbar.value = false
            }
        })
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}