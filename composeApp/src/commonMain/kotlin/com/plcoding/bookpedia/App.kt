package com.plcoding.bookpedia

import androidx.compose.runtime.*
import com.plcoding.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.plcoding.bookpedia.book.data.repository.DefaultBookRepository
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import com.plcoding.bookpedia.core.domain.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(engine: HttpClientEngine) {
    BookListScreenRoot(
        viewModel = remember { BookListViewModel(
            bookRepository = DefaultBookRepository(
                remoteBookDataSource = KtorRemoteBookDataSource(
                    httpClient = HttpClientFactory.create(

                    )
                )
            )
        ) },
        onBookClick = {

        }
    )
}

// Presentation -> Domain <- Data
// All the structure regarding books must be separate inside this 3 layers, and not in whole project,
// Every feature must contain this 3 following structure to maintain as scalable project
// Presentation and Data can access Domain properties, but domain cannot access presentation or data