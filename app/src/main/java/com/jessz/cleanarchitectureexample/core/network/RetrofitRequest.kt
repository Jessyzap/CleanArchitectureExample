package com.jessz.cleanarchitectureexample.core.network

import com.jessz.cleanarchitectureexample.core.utils.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend inline fun <reified T> doRequest(
    crossinline request: suspend () -> Response<T>
): ResponseWrapper<T> {
    return try {

        val response = request()

        withContext(Dispatchers.IO) {
            if (response.isSuccessful) {
                val result = response.body()
                result?.let {
                    ResponseWrapper.SuccessResult(result = it)
                } ?: run {
                    ResponseWrapper.ErrorResult(message = "Erro ao carregar os dados")
                }
            } else {
                ResponseWrapper.ErrorResult(message = response.errorBody()?.string() ?: "Erro desconhecido")
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        ResponseWrapper.ErrorResult(message = e.message.orEmpty())
    }
}