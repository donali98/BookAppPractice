package com.donali.bookapppractice.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donali.bookapppractice.entities.Book

@Dao
interface BookDao {

    @Query("select * from book")
    fun getAllBooks():LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book):Long

    @Query("delete from book")
    fun deleteBooks()
}