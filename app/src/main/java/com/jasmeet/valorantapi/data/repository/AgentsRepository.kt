package com.jasmeet.valorantapi.data.repository


import android.content.Context
import com.google.gson.Gson
import com.jasmeet.valorantapi.data.model.remote.agentData.AgentData
import com.jasmeet.valorantapi.data.model.remote.agentsApiResponse.AgentsData
import com.jasmeet.valorantapi.data.model.remote.agentsApiResponse.Data
import com.jasmeet.valorantapi.data.mapper.agentsMapper.toAgentsData
import com.jasmeet.valorantapi.data.mapper.agentsMapper.toAgentsEntity
import com.jasmeet.valorantapi.data.dao.AgentsDao
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.utils.Utils

class AgentsRepository(
    private val agentsDao: AgentsDao
) {

    private val agentsApiUrl = "https://valorant-api.com/v1/agents"
    private val  gson = Gson()

    suspend fun fetchAgents(context: Context): State<List<Data>> {
        return try {

            if (Utils.isNetworkAvailable(context)) {
                val result = Utils.makeApiCall(agentsApiUrl)
                if (result.isNotEmpty()) {
                    val agentsData = parseApiResponse(result)
                    agentsDao.insertAgents(agents = agentsData.data.map { it.toAgentsEntity() })
                    State.Success(agentsData.data)
                }else{
                    State.Error("Something went wrong!")
                }
            }
            else{
                val cachedAgents = agentsDao.getAllAgents()
                if (cachedAgents.isNotEmpty()){
                    State.Success(cachedAgents.map { it.toAgentsData() })
                }else{
                    State.Error( "No Cached Data available")
                }
            }
        }catch (e:Exception){
            State.Error(e.localizedMessage ?: "An Error Occurred")
        }
    }

    fun getAgentDetails(agentId: String): State<AgentData> {
        return try {
            val agentData = Utils.makeApiCall(id = agentId,url = agentsApiUrl)
            if (agentData.isNotEmpty()) {
                val data = parseAgentData(agentData)
                State.Success(data)
            } else {
                State.Error("Network Error")
            }
        } catch (e: Exception) {
            State.Error(e.message)
        }
    }


    private fun parseAgentData(result: String): AgentData {
        return gson.fromJson(result, AgentData::class.java)
    }


    private fun parseApiResponse(result: String): AgentsData {
        return gson.fromJson(result, AgentsData::class.java)
    }
}