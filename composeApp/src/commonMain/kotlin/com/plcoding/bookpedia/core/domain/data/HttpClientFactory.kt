package com.plcoding.bookpedia.core.domain.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {

    fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine){
            install(ContentNegotiation){
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                     }
                )
            }
            install(HttpTimeout){
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }

        }
    }
}