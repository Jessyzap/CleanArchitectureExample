package com.jessz.cleanarchitectureexample.features.advice.data.datasourse.remote

import com.jessz.cleanarchitectureexample.core.utils.ResponseWrapper
import com.jessz.cleanarchitectureexample.features.advice.data.models.AdviceResponse

interface IAdviceRemoteDataSource {
    suspend fun getAdvice(): ResponseWrapper<AdviceResponse>
}