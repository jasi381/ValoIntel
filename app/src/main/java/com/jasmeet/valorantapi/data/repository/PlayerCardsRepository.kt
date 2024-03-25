package com.jasmeet.valorantapi.data.repository

import android.content.Context
import com.google.gson.Gson
import com.jasmeet.valorantapi.data.dao.PlayerCardsDao
import com.jasmeet.valorantapi.data.mapper.playerCardsMapper.toPlayerCards
import com.jasmeet.valorantapi.data.mapper.playerCardsMapper.toPlayerCardsEntity
import com.jasmeet.valorantapi.data.model.remote.playerCardsApiResponse.Data
import com.jasmeet.valorantapi.data.model.remote.playerCardsApiResponse.PlayerCards
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.utils.Utils

class PlayerCardsRepository(
    private val playerCardsDao: PlayerCardsDao
) {

    private val playerCardsApiUrl = "https://valorant-api.com/v1/playercards"
    private val  gson = Gson()


    suspend fun fetchPlayerCards(context: Context):State<List<Data>>{

        return try{

            if(Utils.isNetworkAvailable(context)){
                val result = Utils.makeApiCall(playerCardsApiUrl)
                if (result.isNotEmpty()){
                    val playerCardsData = parseApiResponse(result)
                    playerCardsDao.insertPlayerCard(playerCard = playerCardsData.data.map { it.toPlayerCardsEntity() })
                    State.Success(playerCardsData.data)
                }else{
                    State.Error("Something went wrong!")
                }
            }else{
                val cachedBuddies = playerCardsDao.getAllPlayerCards()
                if (cachedBuddies.isNotEmpty()){
                    State.Success(cachedBuddies.map { it.toPlayerCards() })
                }else{
                    State.Error("Network Error")
                }
            }

        }catch (e:Exception){
            State.Error(e.message.toString())
        }

    }


    private fun parseApiResponse(result:String): PlayerCards {
        return gson.fromJson(result, PlayerCards::class.java)
    }

}