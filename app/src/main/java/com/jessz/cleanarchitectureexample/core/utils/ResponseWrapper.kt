package com.jessz.cleanarchitectureexample.core.utils

sealed class ResponseWrapper<T> {
    class SuccessResult<T>(val result: T) : ResponseWrapper<T>()
    class ErrorResult<T>(val message: String) : ResponseWrapper<T>()
}