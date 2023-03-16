package com.mexicandeveloper.jpmcexercise.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mexicandeveloper.jpmcexercise.models.SATScore
import com.mexicandeveloper.jpmcexercise.models.School
import com.mexicandeveloper.jpmcexercise.repository.MainRepository
import com.mexicandeveloper.jpmcexercise.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _res = MutableLiveData<Resource<List<School>>>()
    val res: LiveData<Resource<List<School>>>
        get() = _res

    private val _score = MutableLiveData<Resource<List<SATScore>>>()
    val score: LiveData<Resource<List<SATScore>>>
        get() = _score


    var position=0

    fun doSomething() {
        _res.postValue(Resource.loading(null))
    }

    fun getSchools() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getSchools().let {
            if (it.isSuccessful) {
                _res.postValue(Resource.success(it.body()))
            } else {
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }

        }
    }
    fun getSATScore()=viewModelScope.launch {
        _score.postValue(Resource.loading(null))
        mainRepository.getSatSchore(getOneSchool()?.dbn?:"").let {
            if(it.isSuccessful){
                _score.postValue(Resource.success(it.body()))
            }else{
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

    fun getOneSchool():School?{
        return res.value?.data?.get(position)
    }

}