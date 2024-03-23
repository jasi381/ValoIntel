package com.jasmeet.valorantapi.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.valorantapi.data.model.remote.currencyApiResponse.Data
import com.jasmeet.valorantapi.data.model.remote.currencyDetails.CurrencyDetails
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

    private val _currencyDetails = MutableLiveData<State<CurrencyDetails>>()
    val currencyDetails: LiveData<State<CurrencyDetails>> = _currencyDetails

    fun fetchCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _currencyApiResponse.postValue(State.Loading)
                val result = repository.fetchCurrencies(context)
                if(result is State.Success) {
                    _currencyApiResponse.postValue(result)
                }
            } catch (e: Exception) {
                _currencyApiResponse.postValue(State.Error(e.localizedMessage))
            }
        }
    }

    fun fetchCurrencyData(currencyId :String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _currencyDetails.postValue(State.Loading)
                val result = repository.getCurrencyDetails(currencyId)
                if(result is State.Success) {
                    _currencyDetails.postValue(result)
                }
            } catch (e: Exception) {
                _currencyDetails.postValue(State.Error(e.localizedMessage))
            }

        }
    }

}