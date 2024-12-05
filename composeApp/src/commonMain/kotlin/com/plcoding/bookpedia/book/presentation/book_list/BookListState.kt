package com.plcoding.bookpedia.book.presentation.book_list

import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.presentation.UiText

//MVI Architecture which means that de INTENT is when some UI user action occurred in screens,
//and then we send to viewmodel, e.g Click in a button, select a book, click in details
data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = books,
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)


 val books = (1  .. 100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "http:Test",
        authors = listOf("Philipp Lackner"),
        description = "Description $it",
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.6789,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3
    )
}
