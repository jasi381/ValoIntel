package com.jasmeet.valorantapi.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.valorantapi.data.model.remote.agentData.AgentData
import com.jasmeet.valorantapi.data.model.remote.agentsApiResponse.Data
import com.jasmeet.valorantapi.data.repository.AgentsRepository
import com.jasmeet.valorantapi.data.state.State
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

    private val _agentsApiResponse = MutableLiveData<State<List<Data>>>()
    val agentsApiResponse: LiveData<State<List<Data>>> = _agentsApiResponse

    private val _agentDetails = MutableLiveData<State<AgentData>>()
    val agentDetails: LiveData<State<AgentData>> = _agentDetails


    fun fetchAgents() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _agentsApiResponse.postValue(State.Loading)
                val result = repository.fetchAgents(context)
                if(result is State.Success) {
                    _agentsApiResponse.postValue(result)
                }
            } catch (e: Exception) {
                _agentsApiResponse.postValue(State.Error(e.localizedMessage))
            }
        }
    }

    fun fetchAgentData(agentId :String){
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
