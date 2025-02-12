package com.plcoding.bookpedia.book.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BookEntity::class],
    version = 1
)

abstract class FavoriteBookDatabase: RoomDatabase() {
}