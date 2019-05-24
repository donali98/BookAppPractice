package com.donali.bookapppractice.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.donali.bookapppractice.daos.AuthorBooksDao
import com.donali.bookapppractice.entities.Author
import com.donali.bookapppractice.entities.AuthorBooks

class AuthorBooksRepository (val authorBooksDao: AuthorBooksDao){
    @WorkerThread
    suspend fun insert(authorBook:AuthorBooks):Long = authorBooksDao.insert(authorBook)
    fun getAuthorsFromBook(bookId:Long):LiveData<List<Author>> = authorBooksDao.getAuthorsFromBook(bookId)
    fun getAll():LiveData<List<AuthorBooks>> = authorBooksDao.getAll()
}