package com.mexicandeveloper.jpmcexercise.api

import com.mexicandeveloper.jpmcexercise.models.SATScore
import com.mexicandeveloper.jpmcexercise.models.School
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun doGetListHighSchool(): Response<List<School>> =apiService.doGetListHighSchool()

    override suspend fun getSATInfo(dbn: String): Response<List<SATScore>> =apiService.getSATInfo(dbn)

}