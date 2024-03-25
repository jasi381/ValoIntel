package com.jasmeet.valorantapi.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.valorantapi.data.model.remote.buddiesApiResponse.Data
import com.jasmeet.valorantapi.data.repository.BuddiesRepository
import com.jasmeet.valorantapi.data.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BuddiesViewModel @Inject constructor(

    private val repository: BuddiesRepository,
    @ApplicationContext private val context: Context

): ViewModel() {

    private val _buddiesApiResponse = MutableLiveData<State<List<Data>>>()
    val buddiesApiResponse : LiveData<State<List<Data>>> = _buddiesApiResponse

    fun fetchBuddies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _buddiesApiResponse.postValue(State.Loading)
                val result = repository.fetchBuddies(context)
                if (result is State.Success){
                    _buddiesApiResponse.postValue(State.Success(result.data))
                }else{
                    _buddiesApiResponse.postValue(State.Error("Something went wrong"))
                }
            }catch (e: Exception){
                _buddiesApiResponse.postValue(State.Error("Something went wrong"))
            }

        }
    }


}