package com.mexicandeveloper.jpmcexercise.repository

import com.mexicandeveloper.jpmcexercise.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getSchools() = apiHelper.doGetListHighSchool()
    suspend fun getSatSchore(dbn:String) = apiHelper.getSATInfo(dbn)
}