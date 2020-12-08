package com.numa91.marvelob.features.characters.characterDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numa91.marvelob.domain.model.BaseModel
import com.numa91.marvelob.domain.model.CharacterModel
import com.numa91.marvelob.domain.usecase.GetCharacterDetailsUseCase
import com.numa91.marvelob.domain.usecase.GetCharactersUseCase
import com.numa91.marvelob.domain.usecase.base.UseCaseResponse

class CharacterDetailViewModel constructor(private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase) : ViewModel() {

    val characterData = MutableLiveData<CharacterModel?>()
    val showProgressbar = MutableLiveData<Boolean>()
    val errorMsg = MutableLiveData<String>()

    fun getCharacter(id: Long){
        showProgressbar.value = true
        val params = GetCharacterDetailsUseCase.Params(id)
        getCharacterDetailsUseCase.invoke(viewModelScope,params, object :UseCaseResponse<BaseModel>{
            override fun onSuccess(result: BaseModel) {
                characterData.value = result.data.results.firstOrNull()
                showProgressbar.value = false
            }

            override fun onError(apiError: String?) {
                errorMsg.value = if(apiError.isNullOrEmpty()) "ERROR" else apiError
            }
        })
    }

}