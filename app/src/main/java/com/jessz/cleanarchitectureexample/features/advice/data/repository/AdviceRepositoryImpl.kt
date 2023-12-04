package com.jessz.cleanarchitectureexample.features.advice.data.repository

import com.jessz.cleanarchitectureexample.core.utils.ResponseWrapper
import com.jessz.cleanarchitectureexample.features.advice.data.datasourse.remote.IAdviceRemoteDataSource
import com.jessz.cleanarchitectureexample.features.advice.domain.entities.AdviceEntity
import com.jessz.cleanarchitectureexample.features.advice.domain.repository.IAdviceRepository

class AdviceRepositoryImpl(private val adviceDataSource: IAdviceRemoteDataSource): IAdviceRepository {

    override suspend fun getAdvice(): ResponseWrapper<AdviceEntity> {

        return when(val response = adviceDataSource.getAdvice()) {
            is ResponseWrapper.SuccessResult -> ResponseWrapper.SuccessResult(
                AdviceEntity.mapper(
                    response.result
                )
            )
            is ResponseWrapper.ErrorResult -> ResponseWrapper.ErrorResult(response.message)
        }
    }

}