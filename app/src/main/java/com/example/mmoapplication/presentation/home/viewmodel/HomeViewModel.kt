package com.example.mmoapplication.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mmoapplication.data.core.State
import com.example.mmoapplication.data.model.MMODomain
import com.example.mmoapplication.domain.usecase.home.GetAllMMOGamesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getAllMMOGamesUseCase: GetAllMMOGamesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _mmoResponse = MutableLiveData<State<List<MMODomain>>>()
    val mmoResponse: LiveData<State<List<MMODomain>>>
        get() = _mmoResponse

    fun getGamesList() {
        viewModelScope.launch {
            try {
                _mmoResponse.value = State.onLoading(true)
                withContext(ioDispatcher) {
                    getAllMMOGamesUseCase.getGames()
                }.let { gameList ->
                    _mmoResponse.value = State.onSuccess(gameList)
                    _mmoResponse.value = State.onLoading(false)
                }
            } catch (e: Exception) {
                _mmoResponse.value = State.onError(e)
                _mmoResponse.value = State.onLoading(false)
            }
        }
    }
}