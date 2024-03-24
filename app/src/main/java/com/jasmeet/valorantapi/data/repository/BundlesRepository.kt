package com.jasmeet.valorantapi.data.repository

import android.content.Context
import com.google.gson.Gson
import com.jasmeet.valorantapi.data.dao.BundleDao
import com.jasmeet.valorantapi.data.mapper.bundlesMapper.toBundles
import com.jasmeet.valorantapi.data.mapper.bundlesMapper.toBundlesEntity
import com.jasmeet.valorantapi.data.model.remote.bundleDetails.BundleDetails
import com.jasmeet.valorantapi.data.model.remote.bundlesApiResponse.Bundles
import com.jasmeet.valorantapi.data.model.remote.bundlesApiResponse.Data
import com.jasmeet.valorantapi.data.state.State
import com.jasmeet.valorantapi.presentation.utils.Utils

class BundlesRepository(
    private val bundleDao: BundleDao
) {

    private val bundlesApiUrl = "https://valorant-api.com/v1/bundles"
    private val  gson = Gson()

    suspend fun fetchBundles(context: Context):State<List<Data>>{
       return  try {
            if (Utils.isNetworkAvailable(context)) {
                val result = Utils.makeApiCall(bundlesApiUrl)
                if (result.isNotEmpty()){
                    val bundlesData = parseApiResponse(result)
                    bundleDao.insertBundle(bundles = bundlesData.data.map { it.toBundlesEntity() })
                    State.Success(bundlesData.data)
                }else{
                    State.Error("Something went wrong")
                }
            }else{
                val cachedBundles = bundleDao.getAllBundles()
                if (cachedBundles.isNotEmpty()){
                    State.Success(cachedBundles.map { it.toBundles() })
                }else{
                    State.Error("Network Error")
                }
            }
        }catch (e:Exception){
            State.Error(e.message.toString())
        }
    }


    fun getBuddyDetails(buddyId:String): State<BundleDetails> {
        return try{
            val buddyData = Utils.makeApiCall(url = bundlesApiUrl, id = buddyId)
            if (buddyData.isNotEmpty()){
                val data = parseBundleData(buddyData)
                State.Success(data)
            }else{
                State.Error("Network Error")
            }
        }catch (e:Exception){
            State.Error(e.message.toString())
        }
    }

    private fun parseApiResponse(result:String): Bundles {
        return gson.fromJson(result, Bundles::class.java)
    }

    private fun parseBundleData(result:String): BundleDetails {
        return gson.fromJson(result, BundleDetails::class.java)
    }


}