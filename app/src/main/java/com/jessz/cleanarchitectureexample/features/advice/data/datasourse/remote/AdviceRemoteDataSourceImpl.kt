package com.jessz.cleanarchitectureexample.features.advice.data.datasourse.remote

import com.jessz.cleanarchitectureexample.core.utils.ResponseWrapper
import com.jessz.cleanarchitectureexample.core.network.doRequest
import com.jessz.cleanarchitectureexample.features.advice.data.datasourse.apiservice.AdviceApiService
import com.jessz.cleanarchitectureexample.features.advice.data.models.AdviceResponse

class AdviceRemoteDataSourceImpl(private val adviceService: AdviceApiService): IAdviceRemoteDataSource {

    override suspend fun getAdvice(): ResponseWrapper<AdviceResponse> {
        return doRequest { adviceService.getAdvice() }
    }

}