package com.jessz.cleanarchitectureexample.features.advice.di

import com.jessz.cleanarchitectureexample.core.network.RetrofitConfig
import com.jessz.cleanarchitectureexample.features.advice.data.datasourse.apiservice.AdviceApiService
import com.jessz.cleanarchitectureexample.features.advice.data.datasourse.remote.AdviceRemoteDataSourceImpl
import com.jessz.cleanarchitectureexample.features.advice.data.datasourse.remote.IAdviceRemoteDataSource
import com.jessz.cleanarchitectureexample.features.advice.data.repository.AdviceRepositoryImpl
import com.jessz.cleanarchitectureexample.features.advice.domain.repository.IAdviceRepository
import com.jessz.cleanarchitectureexample.features.advice.presentation.AdviceViewModel

object AdviceModule {

    private lateinit var adviceService: AdviceApiService
    private lateinit var adviceDataSource: IAdviceRemoteDataSource
    private lateinit var adviceRepository: IAdviceRepository
    private lateinit var adviceViewModel: AdviceViewModel

    fun provideAdviceModule(){
        provideAdviceService()
        provideDataSource()
        provideRepository()
        provideViewModel()
    }

    private fun provideAdviceService() {
        adviceService = RetrofitConfig.getRetrofit().create(AdviceApiService::class.java)
    }

    private fun provideDataSource() {
        adviceDataSource = AdviceRemoteDataSourceImpl(adviceService)
    }

    private fun provideRepository() {
        adviceRepository = AdviceRepositoryImpl(adviceDataSource)
    }

    private fun provideViewModel() {
        adviceViewModel = AdviceViewModel(adviceRepository)
    }

    fun getViewModel(): AdviceViewModel {
        return adviceViewModel
    }

}