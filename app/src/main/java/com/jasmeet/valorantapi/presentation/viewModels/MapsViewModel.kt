package com.jasmeet.valorantapi.presentation.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.valorantapi.data.model.remote.mapData.MapData
import com.jasmeet.valorantapi.data.model.remote.mapsData.Data
import com.jasmeet.valorantapi.data.repository.MapsRepository
import com.jasmeet.valorantapi.data.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel  @Inject constructor(

    private val repository: MapsRepository,
    @ApplicationContext private val context: Context

) :ViewModel(){

    private val _mapsApiResponse = MutableLiveData<State<List<Data>>>()
    val mapsApiResponse :LiveData<State<List<Data>>> = _mapsApiResponse

    private val _mapDetails = MutableLiveData<State<MapData>>()
    val mapDetails: LiveData<State<MapData>> = _mapDetails


    fun fetchMaps(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _mapsApiResponse.postValue(State.Loading)
                val result = repository.fetchMaps(context)
                if (result is State.Success){
                    _mapsApiResponse.postValue(result)
                }else{
                    _mapsApiResponse.postValue(State.Error("Something went wrong"))
                }
            }catch (e:Exception){
                _mapsApiResponse.postValue(State.Error(e.localizedMessage))
            }
        }
    }

    fun fetchMapData(mapId :String){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                _mapDetails.postValue(State.Loading)
                val result = repository.getMapDetails(mapId)
                Log.d("MapsViewModel", "fetchMapData: $result")
                if(result is State.Success) {
                    _mapDetails.postValue(result)
                }else{
                    _mapDetails.postValue(State.Error("Something went wrong"))
                }
            } catch (e: Exception) {
                _mapDetails.postValue(State.Error(e.localizedMessage))
            }

        }
    }
}