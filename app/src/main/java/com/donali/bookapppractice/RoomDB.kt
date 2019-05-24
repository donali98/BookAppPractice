package com.donali.bookapppractice

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.donali.bookapppractice.daos.AuthorBooksDao
import com.donali.bookapppractice.daos.AuthorDao
import com.donali.bookapppractice.daos.BookDao
import com.donali.bookapppractice.entities.Author
import com.donali.bookapppractice.entities.AuthorBooks
import com.donali.bookapppractice.entities.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Book::class,Author::class,AuthorBooks::class], version = 1,exportSchema = false)

public abstract class RoomDB:RoomDatabase() {

    abstract fun bookDao():BookDao
    abstract fun authorDao():AuthorDao
    abstract fun authorBooksDao():AuthorBooksDao

    private class RoomDBCallback(private val scope:CoroutineScope):RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {database->
                scope.launch(Dispatchers.IO){
                    populateDatabase(database.authorDao(),database.bookDao(),database.authorBooksDao())
                }
            }
        }
        suspend fun populateDatabase(authorDao: AuthorDao,bookDao: BookDao,authorBooksDao: AuthorBooksDao){
            authorBooksDao.deleteAll()
            authorDao.deleteAll()
            bookDao.deleteBooks()

            var author = Author("Gabriel Garcia Marquez")
            Log.d("CUSTOM",author.toString())
            authorDao.insertAuthor(author)
        }
    }


    companion object{
        @Volatile
        private var INSTANCE:RoomDB? = null

        fun getInstance(
            appContext: Context,
            scope:CoroutineScope
        )
        :RoomDB{
            val tmp = INSTANCE
            if(tmp!=null) return tmp
            synchronized(this){
                val instance = Room.databaseBuilder(appContext,RoomDB::class.java,"Library")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}