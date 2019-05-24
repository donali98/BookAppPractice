package com.donali.bookapppractice.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.donali.bookapppractice.daos.BookDao
import com.donali.bookapppractice.entities.Book

class BookRepository(private val bookDao: BookDao) {

    fun getAll():LiveData<List<Book>> = bookDao.getAllBooks()

    @WorkerThread
    suspend fun insert(book: Book):Long = bookDao.insertBook(book)


}