package com.mexicandeveloper.jpmcexercise.api

import com.mexicandeveloper.jpmcexercise.models.SATScore
import com.mexicandeveloper.jpmcexercise.models.School
import retrofit2.Response

interface ApiHelper {

    suspend fun doGetListHighSchool(): Response<List<School>>
    suspend fun getSATInfo(dbn: String): Response<List<SATScore>>

}