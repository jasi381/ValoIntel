package com.jasmeet.valorantapi.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.valorantapi.data.model.remote.playerCardsApiResponse.Data
import com.jasmeet.valorantapi.data.repository.PlayerCardsRepository
import com.jasmeet.valorantapi.data.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerCardsViewModel @Inject constructor(

    private val repository: PlayerCardsRepository,
    @ApplicationContext private val context: Context

): ViewModel() {

    private val _playerCardsApiResponse = MutableLiveData<State<List<Data>>>()
    val playerCardsApiResponse : LiveData<State<List<Data>>> = _playerCardsApiResponse

    fun fetchPlayerCards() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _playerCardsApiResponse.postValue(State.Loading)
                val result = repository.fetchPlayerCards(context)
                if (result is State.Success){
                    _playerCardsApiResponse.postValue(State.Success(result.data))
                }else{
                    _playerCardsApiResponse.postValue(State.Error("Something went wrong"))
                }
            }catch (e: Exception){
                _playerCardsApiResponse.postValue(State.Error("Something went wrong"))
            }

        }
    }


}