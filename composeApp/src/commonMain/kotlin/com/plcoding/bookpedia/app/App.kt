package com.plcoding.bookpedia.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController,
            startDestination =
        )
        val viewModel = koinViewModel<BookListViewModel>()

        BookListScreenRoot(
            viewModel = viewModel ,
            onBookClick = {

            }
        )
    }
    }


// Presentation -> Domain <- Data
// All the structure regarding books must be separate inside this 3 layers, and not in whole project,
// Every feature must contain this 3 following structure to maintain as scalable project
// Presentation and Data can access Domain properties, but domain cannot access presentation or data