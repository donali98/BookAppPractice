package com.donali.bookapppractice.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.donali.bookapppractice.daos.BookDao
import com.donali.bookapppractice.entities.Book
import com.donali.bookapppractice.entities.BookWithAuthors

class BookRepository(private val bookDao: BookDao) {

    fun getAll():LiveData<List<BookWithAuthors>> = bookDao.getAllBooks()

    @WorkerThread
    suspend fun insert(book: Book):Long = bookDao.insertBook(book)

    @WorkerThread
    suspend fun updateFavorite(isFv: Boolean,bookID:Long) = bookDao.updateFavorite(isFv,bookID)


}