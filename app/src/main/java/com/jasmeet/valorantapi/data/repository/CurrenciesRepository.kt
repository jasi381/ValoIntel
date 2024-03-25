package com.jasmeet.valorantapi.data.repository

import android.content.Context
import com.google.gson.Gson
import com.jasmeet.valorantapi.data.dao.CurrenciesDao
import com.jasmeet.valorantapi.data.mapper.currencyMapper.toCurrenciesData
import com.jasmeet.valorantapi.data.mapper.currencyMapper.toCurrencyEntity
import com.jasmeet.valorantapi.data.model.remote.currencyApiResponse.CurrencyData
import com.jasmeet.valorantapi.data.model.remote.currencyApiResponse.Data
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.utils.Utils

/**
 * Created by Jasmeet Singh
 */

class CurrenciesRepository(private val currenciesDao: CurrenciesDao) {

    private val currencyApiUrl = "https://valorant-api.com/v1/currencies"
    private val gson = Gson()

    suspend fun fetchCurrencies(context: Context): State<List<Data>> {
        return try {

            if (Utils.isNetworkAvailable(context)){
                val result = Utils.makeApiCall(currencyApiUrl)
                if (result.isNotEmpty()){
                    val currencyData = parseApiResult(result)
                    currenciesDao.insertCurrency(currencies = currencyData.data.map { it.toCurrencyEntity() })
                    State.Success(currencyData.data)
                }else{
                    State.Error("Something went wrong")
                }
            }else{
                val cachedCurrency = currenciesDao.getAllCurrency()
                if (cachedCurrency.isNotEmpty()){
                    State.Success(cachedCurrency.map { it.toCurrenciesData() })
                }else{
                    State.Error("No Cached Data available")
                }
            }

        }catch (e:Exception){
            e.printStackTrace()
            State.Error(e.message.toString())
        }

    }




    private fun parseApiResult(result:String): CurrencyData {
        return gson.fromJson(result,CurrencyData::class.java)
    }

}