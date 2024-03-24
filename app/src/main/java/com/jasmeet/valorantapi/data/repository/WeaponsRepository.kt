package com.jasmeet.valorantapi.data.repository

import android.content.Context
import com.google.gson.Gson
import com.jasmeet.valorantapi.data.model.remote.weaponData.WeaponData
import com.jasmeet.valorantapi.data.model.remote.weaponsApiResponse.Data
import com.jasmeet.valorantapi.data.model.remote.weaponsApiResponse.WeaponsData
import com.jasmeet.valorantapi.data.mapper.weaponsMapper.toWeaponsData
import com.jasmeet.valorantapi.data.mapper.weaponsMapper.toWeaponsEntity
import com.jasmeet.valorantapi.data.dao.WeaponsDao
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.utils.Utils

class WeaponsRepository (
    private val weaponsDao : WeaponsDao

){

    private val weaponsApiUrl = "https://valorant-api.com/v1/weapons"
    private val gson = Gson()

    suspend fun fetchWeapons(context: Context): State<List<Data>> {
        return try{
            if (Utils.isNetworkAvailable(context)){
                val result = Utils.makeApiCall(weaponsApiUrl)
                if (result.isNotEmpty()){
                    val weaponsData = parseApiResponse(result)
                    weaponsDao.insertWeapons(weapons =  weaponsData.data.map { it.toWeaponsEntity() })
                    State.Success(weaponsData.data)
                }else{
                    State.Error("Something went wrong!")
                }
            }else{
                val cachedAgents = weaponsDao.getAllWeapons()
                if (cachedAgents.isNotEmpty()){
                    State.Success(cachedAgents.map { it.toWeaponsData() })
                }else{
                    State.Error( "No Cached Data available")
                }
            }
        }catch (e:Exception){
            State.Error(e.localizedMessage ?: "An Error Occurred")
        }
    }

    fun getWeaponsDetails(weaponId: String): State<WeaponData> {
        return try {
            val weaponsData = Utils.makeApiCall(id = weaponId,url = weaponsApiUrl)
            if (weaponsData.isNotEmpty()) {
                val data = parseWeaponData(weaponsData)
                State.Success(data)
            } else {
                State.Error("Network Error")
            }
        } catch (e: Exception) {
            State.Error(e.message)
        }
    }


    private fun parseWeaponData(result: String): WeaponData {
        return gson.fromJson(result, WeaponData::class.java)
    }

    private fun parseApiResponse(result: String): WeaponsData {
        return gson.fromJson(result, WeaponsData::class.java)
    }


}