package com.jasmeet.valorantapi.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.jasmeet.valorantapi.data.apiResponse.AgentsData
import com.jasmeet.valorantapi.data.apiResponse.Data
import com.jasmeet.valorantapi.data.agentData.AgentData
import com.jasmeet.valorantapi.mapper.toAgentsData
import com.jasmeet.valorantapi.mapper.toAgentsEntity
import com.jasmeet.valorantapi.room.AgentsDao
import com.jasmeet.valorantapi.state.State
import com.jasmeet.valorantapi.utils.Utils
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class AgentsRepository(
    private val agentsDao: AgentsDao
) {

    private val agentsApiUrl = "https://valorant-api.com/v1/agents"

    suspend fun fetchAgents(context: Context): State<List<Data>> {
        return try {

            if (Utils.isNetworkAvailable(context)) {
                val result = makeApiCall()
                if (result.isNotEmpty()) {
                    val agentsData = parseApiResponse(result)
                    agentsDao.insertAgents(agents = agentsData.data.map { it.toAgentsEntity() })
                    State.Success(agentsData.data)
                }else{
                    State.Error("Something went wrong !")
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
            val agentData = makeApiCall(agentId)
            if (agentData.isNotEmpty()) {
                Log.d("AgentData", "getAgentDetails: $agentData")
                val data = parseAgentData(agentData)
                Log.d("AgentData2", "getAgentDetails: $data")
                State.Success(data)
            } else {
                State.Error("Network Error")
            }
        } catch (e: Exception) {
            State.Error(e.message)
        }
    }

    private fun makeApiCall(agentId: String): String {
        val url = "https://valorant-api.com/v1/agents/$agentId"
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(
                httpLoggingInterceptor.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

        val request = Request.Builder()
            .url(url)
            .build()

        return try {
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                response.body?.string() ?: ""
            } else {
                throw IOException("Error: ${response.code}")
            }
        } catch (e: IOException) {
            throw e
        }

    }

    private fun parseAgentData(result: String): AgentData {
        val gson = Gson()
        return gson.fromJson(result, AgentData::class.java)
    }


    private fun parseApiResponse(result: String): AgentsData {
        val gson = Gson()
        return gson.fromJson(result, AgentsData::class.java)
    }


    private fun makeApiCall(): String {

        val httpLoggingInterceptor = HttpLoggingInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(
                httpLoggingInterceptor.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()


        val request = Request.Builder()
            .url(agentsApiUrl)
            .build()

        return try {
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                response.body?.string() ?: ""
            } else {
                throw IOException("Error: ${response.code}")
            }
        } catch (e: IOException) {
            throw e
        }

    }
}