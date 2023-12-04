package com.jessz.cleanarchitectureexample.features.advice.domain.entities

import com.jessz.cleanarchitectureexample.features.advice.data.models.AdviceResponse
import java.io.Serializable

data class AdviceEntity(
    val slip: Advice
) : Serializable {

    data class Advice(
        val id: String?,
        val advice: String
    ) : Serializable

    companion object {
        fun mapper(response: AdviceResponse) =
            AdviceEntity(
                slip = Advice(
                    id = response.slip.id,
                    advice = response.slip.advice.orEmpty()
                )
            )
    }

}