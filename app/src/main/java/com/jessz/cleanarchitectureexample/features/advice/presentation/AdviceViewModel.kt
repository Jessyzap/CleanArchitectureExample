package com.jessz.cleanarchitectureexample.features.advice.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jessz.cleanarchitectureexample.core.utils.ResponseWrapper
import com.jessz.cleanarchitectureexample.core.utils.ViewState
import com.jessz.cleanarchitectureexample.features.advice.domain.entities.AdviceEntity
import com.jessz.cleanarchitectureexample.features.advice.domain.repository.IAdviceRepository
import kotlinx.coroutines.launch

class AdviceViewModel(private val adviceRepository: IAdviceRepository) : ViewModel() {

    private val _advice = MutableLiveData<ViewState<AdviceEntity>>(ViewState.Loading())
    val advice: LiveData<ViewState<AdviceEntity>>
        get() = _advice

    fun getAdvice() {
        _advice.value = ViewState.Loading()

        viewModelScope.launch {
            when (val response = adviceRepository.getAdvice()) {
                is ResponseWrapper.SuccessResult -> {
                    _advice.value = ViewState.Success(result = response.result)
                }

                is ResponseWrapper.ErrorResult -> {
                    _advice.value = ViewState.Error(message = response.message)
                }
            }
        }
    }

}