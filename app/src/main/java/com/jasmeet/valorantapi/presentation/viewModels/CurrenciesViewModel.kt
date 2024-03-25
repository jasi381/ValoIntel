package com.jasmeet.valorantapi.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.valorantapi.data.model.remote.currencyApiResponse.Data
import com.jasmeet.valorantapi.data.repository.CurrenciesRepository
import com.jasmeet.valorantapi.data.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(

    private val repository: CurrenciesRepository,
    @ApplicationContext private val context : Context

) : ViewModel() {

    private val _currencyApiResponse = MutableLiveData<State<List<Data>>>()
    val currencyApiResponse: LiveData<State<List<Data>>> = _currencyApiResponse

    fun fetchCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _currencyApiResponse.postValue(State.Loading)
                val result = repository.fetchCurrencies(context)
                if(result is State.Success) {
                    _currencyApiResponse.postValue(result)
                }else{
                    _currencyApiResponse.postValue(State.Error("Something went wrong"))
                }
            } catch (e: Exception) {
                _currencyApiResponse.postValue(State.Error(e.localizedMessage))
            }
        }
    }

}