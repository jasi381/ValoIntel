package com.jasmeet.valorantapi

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.valorantapi.data.AgentsData
import com.jasmeet.valorantapi.data.Data
import com.jasmeet.valorantapi.repository.AgentsRepository
import com.jasmeet.valorantapi.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AgentsViewModel @Inject constructor(

    private val repository: AgentsRepository,
    @ApplicationContext private val context :Context

) :ViewModel() {

    private val _apiResponse = MutableLiveData<State<List<Data>>>()
    val apiResponse: LiveData<State<List<Data>>> = _apiResponse

    private val _agentDetails = MutableLiveData<State<AgentsData>>()
    val agentDetails: LiveData<State<AgentsData>> = _agentDetails


    fun fetchAgents() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _apiResponse.postValue(State.Loading)
                val result = repository.fetchAgents(context)
                if(result is State.Success) {
                    _apiResponse.postValue(result)
                }
            } catch (e: Exception) {
                _apiResponse.postValue(State.Error(e.localizedMessage))
            }
        }
    }

    fun fetchAgentDate(agentId :String){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                _agentDetails.postValue(State.Loading)
                val result = repository.getAgentDetails(agentId)
                if(result is State.Success) {
                    _agentDetails.postValue(result)
                }
            } catch (e: Exception) {
                _agentDetails.postValue(State.Error(e.localizedMessage))
            }

        }
    }
}
