@file:OptIn(FlowPreview::class)

package com.plcoding.bookpedia.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.plcoding.bookpedia.book.data.repository.DefaultBookRepository
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//Presentation -> Domain <- Data
//If i want to access something from DATA in presentation or domain I need to work with
//ABSTRACTION to follow the MVVM rule
class BookListViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()

    private var cachedBooks = emptyList<Book>()

    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnBookClick -> {


            }

            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }

            }

            is BookListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }

            }
        }
    }

    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L) //timer applied to only search the books if the user didn't type for 500Mls
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchResults = cachedBooks
                            )
                        }
                    }
                    query.length >= 2 -> {
                        searchBooks(query)
                    }

                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) {
        _state.update { it.copy(
            isLoading = true
        ) }
        viewModelScope.launch {
            bookRepository
                .searchBooks(query)
                .onSuccess { searchResults ->
                    _state.update { it.copy(
                        isLoading = true,
                        errorMessage = null,
                        searchResults = searchResults
                    ) }
                }

        }
    }
}