package com.mexicandeveloper.jpmcexercise.api

import com.mexicandeveloper.jpmcexercise.models.SATScore
import com.mexicandeveloper.jpmcexercise.models.School
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("resource/s3k6-pzi2.json")
    suspend fun doGetListHighSchool(): Response<List<School>>

    @Headers("X-App-Token:StcRVpQvYxSqaVDG62UPdrohp")
    @GET("resource/f9bf-2cp4.json")
    suspend fun getSATInfo(@Query("dbn") dbn: String?): Response<List<SATScore>>


}