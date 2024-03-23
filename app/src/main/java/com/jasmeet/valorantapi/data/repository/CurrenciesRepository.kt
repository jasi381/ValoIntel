package com.jasmeet.valorantapi.data.repository

import android.content.Context
import com.google.gson.Gson
import com.jasmeet.valorantapi.data.model.remote.currencyApiResponse.CurrencyData
import com.jasmeet.valorantapi.data.model.remote.currencyApiResponse.Data
import com.jasmeet.valorantapi.data.mapper.toCurrenciesData
import com.jasmeet.valorantapi.data.mapper.toCurrencyEntity
import com.jasmeet.valorantapi.data.dao.CurrenciesDao
import com.jasmeet.valorantapi.data.model.remote.currencyDetails.CurrencyDetails
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

    fun getCurrencyDetails(currencyId: String): State<CurrencyDetails> {
        return try {
            val currencyData = Utils.makeApiCall(id = currencyId,url = currencyApiUrl)
            if (currencyData.isNotEmpty()) {
                val data = parseCurrencyDetails(currencyData)
                State.Success(data)
            } else {
                State.Error("Network Error")
            }
        } catch (e: Exception) {
            State.Error(e.message)
        }
    }



    private fun parseApiResult(result:String): CurrencyData {
        return gson.fromJson(result,CurrencyData::class.java)
    }

    private fun parseCurrencyDetails(result: String): CurrencyDetails {
        return gson.fromJson(result, CurrencyDetails::class.java)
    }
}