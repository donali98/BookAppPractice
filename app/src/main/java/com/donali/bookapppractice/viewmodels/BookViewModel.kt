package com.donali.bookapppractice.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.donali.bookapppractice.RoomDB
import com.donali.bookapppractice.entities.Author
import com.donali.bookapppractice.entities.AuthorBooks
import com.donali.bookapppractice.entities.Book
import com.donali.bookapppractice.repositories.AuthorBooksRepository
import com.donali.bookapppractice.repositories.AuthorRepository
import com.donali.bookapppractice.repositories.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(app: Application): AndroidViewModel(app) {
    private val bookRepository:BookRepository
    private val authorRepository:AuthorRepository
    private val authorBooksRepository:AuthorBooksRepository

    var insertionId = MutableLiveData<Long>()

    init {
        val bookDao = RoomDB.getInstance(app,viewModelScope).bookDao()
        val authorDao = RoomDB.getInstance(app,viewModelScope).authorDao()
        val authorBookDao = RoomDB.getInstance(app,viewModelScope).authorBooksDao()
        bookRepository = BookRepository(bookDao)
        authorRepository = AuthorRepository(authorDao)
        authorBooksRepository = AuthorBooksRepository(authorBookDao)
    }

    fun getAllBooks():LiveData<List<Book>> = bookRepository.getAll()
    fun getAllAuthors():LiveData<List<Author>> = authorRepository.getAll()
    fun getAllAuthorBooks():LiveData<List<AuthorBooks>> = authorBooksRepository.getAll()


    fun insertBook(book: Book) = viewModelScope.launch(Dispatchers.IO){
        val bookId = bookRepository.insert(book)
        insertionId.postValue(bookId)
    }

    fun insertAuthor(author:Author) = viewModelScope.launch (Dispatchers.IO){
        authorRepository.insert(author)
    }
    fun insertAuthorBook(authorBook: AuthorBooks) = viewModelScope.launch (Dispatchers.IO){
        authorBooksRepository.insert(authorBook)
    }


}