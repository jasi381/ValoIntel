package com.jasmeet.valorantapi.data.repository

import android.content.Context
import com.google.gson.Gson
import com.jasmeet.valorantapi.data.dao.SpraysDao
import com.jasmeet.valorantapi.data.mapper.spraysMapper.toSpray
import com.jasmeet.valorantapi.data.mapper.spraysMapper.toSpraysEntity
import com.jasmeet.valorantapi.data.model.remote.sprayDetails.SprayDetails
import com.jasmeet.valorantapi.data.model.remote.spraysApiResponse.Data
import com.jasmeet.valorantapi.data.model.remote.spraysApiResponse.SpraysResponse
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.utils.Utils

class SpraysRepository(
    private val spraysDao: SpraysDao
) {

    private val spraysApiUrl = "https://valorant-api.com/v1/sprays"
    private val  gson = Gson()


    suspend fun fetchSprays(context: Context):State<List<Data>>{
        return try{
            if(Utils.isNetworkAvailable(context)){
                val result = Utils.makeApiCall(spraysApiUrl)
                if (result.isNotEmpty()){
                    val spraysData = parseApiResponse(result)
                    spraysDao.insertSprays(sprays = spraysData.data.map { it.toSpraysEntity() })
                    State.Success(spraysData.data)
                }else{
                    State.Error("Something went wrong!")
                }

            }else{
                val cachedBuddies = spraysDao.getAllSprays()
                if (cachedBuddies.isNotEmpty()){
                    State.Success(cachedBuddies.map { it.toSpray() })
                }else{
                    State.Error("Network Error")
                }
            }

        }catch (e:Exception){
            State.Error(e.message.toString())
        }

    }

    fun getSprayDetails(sprayId:String):State<SprayDetails>{

        return try{
            val sprayData = Utils.makeApiCall(url = spraysApiUrl, id = sprayId)
            if (sprayData.isNotEmpty()){
                val data = parseSprayData(sprayData)
                State.Success(data)
            }else{
                State.Error("Network Error")
            }
        }catch (e:Exception){
            State.Error(e.message.toString())
        }

    }

    private fun parseApiResponse(result:String): SpraysResponse {
        return gson.fromJson(result, SpraysResponse::class.java)
    }

    private fun parseSprayData(result:String): SprayDetails {
        return gson.fromJson(result, SprayDetails::class.java)
    }
}