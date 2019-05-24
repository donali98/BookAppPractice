package com.donali.bookapppractice.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donali.bookapppractice.entities.Author
import com.donali.bookapppractice.entities.AuthorBooks
import com.donali.bookapppractice.entities.Book

@Dao
interface AuthorBooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(authorBook: AuthorBooks):Long

    @Query("select * from author_books")
    fun getAll():LiveData<List<AuthorBooks>>
    @Query(
        """
           SELECT * FROM author
           INNER JOIN author_books
           ON author.id=author_books.authorId
           WHERE author_books.bookId=:idBook
           """
    )
    fun getAuthorsFromBook(idBook: Long): LiveData<List<Author>>


    @Query(
        """
           SELECT * FROM book
           INNER JOIN author_books
           ON book.id=author_books.bookId
           WHERE author_books.authorId=:idAuthor
           """
    )
    fun getBooksFromAuthor(idAuthor: Long): LiveData<List<Book>>

    @Query("delete from author_books")
    fun deleteAll()
}