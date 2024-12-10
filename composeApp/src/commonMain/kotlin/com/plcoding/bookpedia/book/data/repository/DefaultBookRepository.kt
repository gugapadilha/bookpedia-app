package com.plcoding.bookpedia.book.data.repository

import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
) {

}