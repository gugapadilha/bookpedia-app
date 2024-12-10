package com.plcoding.bookpedia.book.data.network

import com.plcoding.bookpedia.book.data.dto.SearchResponseDto
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.DataError.Remote
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.data.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
) {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ) : Result<SearchResponseDto, DataError.Remote>{
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/search.json"
            ) {
                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter("fields", "key,title,language,cover_i,author_key,author_name,cover_edition_key,first_publish_year,ratings_average,ratings_count,number_of_pages_median,edition_count")
            }
        }

    }
}