package com.donali.bookapppractice.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.donali.bookapppractice.daos.AuthorDao
import com.donali.bookapppractice.entities.Author

class AuthorRepository(val authorDao: AuthorDao) {

    fun getAuthorById(auId:Long):LiveData<Author> = authorDao.getAuthorById(auId)

    fun getAll():LiveData<List<Author>> = authorDao.getAllAuthors()

    @WorkerThread
    suspend fun insert(author: Author):Long = authorDao.insertAuthor(author)
}