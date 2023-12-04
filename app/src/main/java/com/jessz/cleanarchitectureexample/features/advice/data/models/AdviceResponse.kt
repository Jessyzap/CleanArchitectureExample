package com.jessz.cleanarchitectureexample.features.advice.data.models

import java.io.Serializable

data class AdviceResponse(
    val slip: Advice
) : Serializable {

    data class Advice(
        val id: String?,
        val advice: String?
    ) : Serializable

}