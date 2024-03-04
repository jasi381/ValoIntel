package com.jasmeet.valorantapi.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.valorantapi.data.apiResponse.Data
import com.jasmeet.valorantapi.data.agentData.AgentData
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

    private val _agentDetails = MutableLiveData<State<AgentData>>()
    val agentDetails: LiveData<State<AgentData>> = _agentDetails


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

    fun fetchAgentData(agentId :String){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                _agentDetails.postValue(State.Loading)
                val result = repository.getAgentDetails(agentId)
                Log.d("TAG", "fetchAgentData: $result")
                if(result is State.Success) {
                    _agentDetails.postValue(result)
                }
            } catch (e: Exception) {
                _agentDetails.postValue(State.Error(e.localizedMessage))
            }

        }
    }
}
