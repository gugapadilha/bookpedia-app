package com.plcoding.bookpedia.book.data.network

import com.plcoding.bookpedia.book.domain.Book
import io.ktor.client.HttpClient

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
) {
    suspend fun searchBooks(query: String, resultLimit: Int? = null) : Result<List<Book>, DataError>
}