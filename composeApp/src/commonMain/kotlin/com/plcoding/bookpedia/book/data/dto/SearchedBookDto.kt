package com.plcoding.bookpedia.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedBookDto(
    @SerialName("key")
    val id: String,
    val languages: List<String>? = null,
    @SerialName("cover_i")
    val coverAlternativeKey: Int? = null,
    @SerialName("author_key")
    val authorKeys: List<String>? = null,
    @SerialName("author_name")
    val authorNames: List<String>? = null,

)
