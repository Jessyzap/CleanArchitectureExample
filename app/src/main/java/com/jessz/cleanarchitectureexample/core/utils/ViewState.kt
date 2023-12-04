package com.jessz.cleanarchitectureexample.core.utils

sealed class ViewState<T> {
    class Success<T>(val result: T) : ViewState<T>()
    class Error<T>(val message: String) : ViewState<T>()
    class Loading<T> : ViewState<T>()
}