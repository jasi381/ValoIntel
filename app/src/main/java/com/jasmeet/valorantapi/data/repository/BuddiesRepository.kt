package com.jasmeet.valorantapi.data.repository

import android.content.Context
import com.google.gson.Gson
import com.jasmeet.valorantapi.data.dao.BuddiesDao
import com.jasmeet.valorantapi.data.mapper.buddiesMapper.toBuddiesData
import com.jasmeet.valorantapi.data.mapper.buddiesMapper.toBuddiesEntity
import com.jasmeet.valorantapi.data.model.remote.buddiesApiResponse.Buddies
import com.jasmeet.valorantapi.data.model.remote.buddiesApiResponse.Data
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.utils.Utils

class BuddiesRepository(
    private val buddiesDao: BuddiesDao
) {

    private val buddieApiUrl = "https://valorant-api.com/v1/buddies"
    private val  gson = Gson()


    suspend fun fetchBuddies(context: Context):State<List<Data>>{

        return try{

            if(Utils.isNetworkAvailable(context)){
                val result = Utils.makeApiCall(buddieApiUrl)
                if (result.isNotEmpty()){
                    val buddiesData = parseApiResponse(result)
                    buddiesDao.insertBuddy(buddies = buddiesData.data.map { it.toBuddiesEntity() })
                    State.Success(buddiesData.data)
                }else{
                    State.Error("Something went wrong!")
                }
            }else{
                val cachedBuddies = buddiesDao.getAllBuddies()
                if (cachedBuddies.isNotEmpty()){
                    State.Success(cachedBuddies.map { it.toBuddiesData() })
                }else{
                    State.Error("Network Error")
                }
            }

        }catch (e:Exception){
            State.Error(e.message.toString())
        }

    }


    private fun parseApiResponse(result:String):Buddies{
        return gson.fromJson(result,Buddies::class.java)
    }

}