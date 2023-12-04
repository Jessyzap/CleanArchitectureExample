package com.jessz.cleanarchitectureexample.features.advice.domain.repository

import com.jessz.cleanarchitectureexample.core.utils.ResponseWrapper
import com.jessz.cleanarchitectureexample.features.advice.domain.entities.AdviceEntity

interface IAdviceRepository {

    suspend fun getAdvice(): ResponseWrapper<AdviceEntity>

}