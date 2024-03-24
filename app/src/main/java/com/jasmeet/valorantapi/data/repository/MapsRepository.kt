package com.jasmeet.valorantapi.data.repository

import android.content.Context
import com.google.gson.Gson
import com.jasmeet.valorantapi.data.model.remote.mapData.MapData
import com.jasmeet.valorantapi.data.model.remote.mapsData.Data
import com.jasmeet.valorantapi.data.model.remote.mapsData.MapsData
import com.jasmeet.valorantapi.data.mapper.mapsMapper.toMapsData
import com.jasmeet.valorantapi.data.mapper.mapsMapper.toMapsEntity
import com.jasmeet.valorantapi.data.dao.MapsDao
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.utils.Utils

class MapsRepository(private val mapsDao: MapsDao) {

    private val mapsApiUrl = "https://valorant-api.com/v1/maps"
    private val gson = Gson()

    suspend fun fetchMaps(context: Context): State<List<Data>> {
        return try {
            if (Utils.isNetworkAvailable(context)){
                val result = Utils.makeApiCall(mapsApiUrl)
                if (result.isNotEmpty()){
                    val mapsData = parseApiResult(result)
                    mapsDao.insertMaps(maps = mapsData.data.map { it.toMapsEntity() })
                    State.Success(mapsData.data)
                }else{
                    State.Error("Something went wrong!")
                }
            }else{
                val cachedMaps = mapsDao.getAllMaps()
                if (cachedMaps.isNotEmpty()){
                    State.Success(cachedMaps.map { it.toMapsData() })
                }else{
                    State.Error("No Cached Data available")
                }
            }


        }catch (e:Exception){
            State.Error(e.localizedMessage ?: "An Error Occurred")}

    }

    fun getMapDetails(mapId:String): State<MapData> {

        return try {
            val agentData = Utils.makeApiCall(id = mapId,url = mapsApiUrl)
            if (agentData.isNotEmpty()) {
                val data = parseMapData(agentData)
                State.Success(data)
            } else {
                State.Error("Network Error")
            }
        } catch (e: Exception) {
            State.Error(e.message)
        }
    }


    private fun parseMapData(result: String): MapData {
        return gson.fromJson(result, MapData::class.java)
    }

    private fun parseApiResult(result: String): MapsData {
        return gson.fromJson(result, MapsData::class.java)

    }
}