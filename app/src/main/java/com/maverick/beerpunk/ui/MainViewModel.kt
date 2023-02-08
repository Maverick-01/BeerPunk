package com.maverick.beerpunk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverick.beerpunk.api.Repository
import com.maverick.beerpunk.model.BeerModel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _getBeerObservable = MutableLiveData<BeerModel>()
    val getBeerObservable: LiveData<BeerModel> = _getBeerObservable

    fun getAllBeers() {
        viewModelScope.launch {
            Repository().getAllBeers().let {
                _getBeerObservable.postValue(it.body())
            }
        }
    }
}
