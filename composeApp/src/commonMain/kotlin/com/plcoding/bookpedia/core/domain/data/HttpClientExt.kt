
package com.plcoding.bookpedia.core.domain.data

import com.plcoding.bookpedia.core.domain.DataError
import io.ktor.client.call.NoTransformationFoundException
import com.plcoding.bookpedia.core.domain.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
) :Result <T, DataError.Remote>{
    return when(response.status.value){
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            }
            catch (e:NoTransformationFoundException){
                Result.Error(DataError.Remote.SERIALIZATION)
            }
        }
        408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)

    }
}