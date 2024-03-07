package com.jasmeet.valorantapi.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.valorantapi.data.weaponData.WeaponData
import com.jasmeet.valorantapi.data.weaponsApiResponse.Data
import com.jasmeet.valorantapi.repository.WeaponsRepository
import com.jasmeet.valorantapi.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeaponsViewModel  @Inject constructor(
    private val repository: WeaponsRepository,
    @ApplicationContext private val context: Context

) :ViewModel() {


    private val _weaponsApiResponse = MutableLiveData<State<List<Data>>>()
    val weaponsApiResponse: LiveData<State<List<Data>>> = _weaponsApiResponse

    private val _weaponDetails = MutableLiveData<State<WeaponData>>()
    val weaponDetails: LiveData<State<WeaponData>> = _weaponDetails


    fun fetchWeapons() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _weaponsApiResponse.postValue(State.Loading)
                val result = repository.fetchWeapons(context)
                if(result is State.Success) {
                    _weaponsApiResponse.postValue(result)
                }
            } catch (e: Exception) {
                _weaponsApiResponse.postValue(State.Error(e.localizedMessage))
            }
        }
    }

    fun fetchWeaponsData(agentId :String){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                _weaponDetails.postValue(State.Loading)
                val result = repository.getWeaponsDetails(agentId)
                Log.d("WeaponsViewModel", "fetchWeaponsData: $result")
                if(result is State.Success) {
                    _weaponDetails.postValue(result)
                }
            } catch (e: Exception) {
                _weaponDetails.postValue(State.Error(e.localizedMessage))
            }

        }
    }
}