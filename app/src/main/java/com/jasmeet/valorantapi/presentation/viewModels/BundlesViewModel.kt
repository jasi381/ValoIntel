package com.jasmeet.valorantapi.presentation.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasmeet.valorantapi.data.model.remote.bundlesApiResponse.Data
import com.jasmeet.valorantapi.data.repository.BundlesRepository
import com.jasmeet.valorantapi.data.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BundlesViewModel @Inject constructor(

    private val repository: BundlesRepository,
    @ApplicationContext private val context: Context

): ViewModel() {

    private val _bundlesApiResponse = MutableLiveData<State<List<Data>>>()
    val bundlesApiResponse : LiveData<State<List<Data>>> = _bundlesApiResponse



    fun fetchBundles() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _bundlesApiResponse.postValue(State.Loading)
                val result = repository.fetchBundles(context)
                Log.d("TAG", "fetchBundles: $result")
                if (result is State.Success){
                    _bundlesApiResponse.postValue(State.Success(result.data))
                }else{
                    _bundlesApiResponse.postValue(State.Error("Something went wrong"))
                }
            }catch (e: Exception){
                _bundlesApiResponse.postValue(State.Error("Something went wrong"))
            }

        }
    }


}