package com.jasmeet.valorantapi.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.valorantapi.data.model.remote.spraysApiResponse.Data
import com.jasmeet.valorantapi.data.repository.SpraysRepository
import com.jasmeet.valorantapi.data.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpraysViewModel @Inject constructor(

    private val repository: SpraysRepository,
    @ApplicationContext private val context: Context

): ViewModel() {

    private val _spraysApiResponse = MutableLiveData<State<List<Data>>>()
    val spraysApiResponse : LiveData<State<List<Data>>> = _spraysApiResponse

    fun fetchSprays() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _spraysApiResponse.postValue(State.Loading)
                val result = repository.fetchSprays(context)
                if (result is State.Success){
                    _spraysApiResponse.postValue(State.Success(result.data))
                }else{
                    _spraysApiResponse.postValue(State.Error("Something went wrong"))
                }
            }catch (e: Exception){
                _spraysApiResponse.postValue(State.Error("Something went wrong"))
            }

        }
    }


}