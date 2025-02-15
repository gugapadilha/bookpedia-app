
package com.plcoding.bookpedia.core.domain.data

import com.plcoding.bookpedia.core.domain.DataError
import io.ktor.client.call.NoTransformationFoundException
import com.plcoding.bookpedia.core.domain.Result
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun  <reified T> safeCall(
    execute: () -> HttpResponse
) : Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException){
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception){
        coroutineContext.ensureActive()
        return Result.Error(DataError.Remote.UNKNOWN)
    }
    return responseToResult(response)
}

//REIFIED is used to access the generic type in execution time(normally generic types are
//extinguish in the compile moments, so, using reified we prevent that.
//INLINE function is used to include the body in which case that is called so it can access reified
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
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Remote.SERVER)
        else -> Result.Error(DataError.Remote.UNKNOWN)

    }
}