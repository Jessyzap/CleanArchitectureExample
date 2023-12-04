package com.jessz.cleanarchitectureexample.features.advice.data.datasourse.apiservice

import com.jessz.cleanarchitectureexample.features.advice.data.models.AdviceResponse
import retrofit2.Response
import retrofit2.http.GET

interface AdviceApiService {

    @GET("advice")
    suspend fun getAdvice(): Response<AdviceResponse>

}