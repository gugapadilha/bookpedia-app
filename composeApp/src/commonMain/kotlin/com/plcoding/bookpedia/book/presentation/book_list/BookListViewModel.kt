package com.plcoding.bookpedia.book.presentation.book_list
import androidx.lifecycle.ViewModel
import com.plcoding.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.plcoding.bookpedia.book.data.repository.DefaultBookRepository
import com.plcoding.bookpedia.book.domain.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//Presentation -> Domain <- Data
//If i want to access something from DATA in presentation or domain I need to work with
//ABSTRACTION to follow the MVVM rule
class BookListViewModel(
    private val dataSource : BookRepository
): ViewModel() {
    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()

    fun onAction(action: BookListAction){
        when(action){
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
}