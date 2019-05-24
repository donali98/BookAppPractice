package com.donali.bookapppractice


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.donali.bookapppractice.entities.AuthorBooks
import com.donali.bookapppractice.entities.Book
import com.donali.bookapppractice.helpers.ActivityHelper
import com.donali.bookapppractice.viewmodels.BookViewModel
import java.security.acl.Owner

class BookFragment : Fragment() {
    lateinit var bookList:RecyclerView
    lateinit var activityHelper: ActivityHelper
    lateinit var bookViewModel:BookViewModel
    lateinit var btnAdd:Button
    lateinit var etTitle:EditText


    var tempAuthorId:Long = 0
    var tempBookId:Long = 0

    val bookAdapter = BookAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityHelper = context as ActivityHelper
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_book, container, false)
        bookList = view.findViewById(R.id.rv_books)
        btnAdd = view.findViewById(R.id.btn_add)
        etTitle = view.findViewById(R.id.et_title)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookList.apply{
            setHasFixedSize(true)
            adapter = bookAdapter
            layoutManager = activityHelper.getLayoutManager()
        }
        bookViewModel.getAllBooks().observe(this, Observer {
            bookAdapter.setData(it)
        })
        bookViewModel.getAllAuthors().observe(this, Observer {
            for (author in it){
                tempAuthorId = author.id
                break
            }
        })

        bookViewModel.insertionId.observe(this, Observer {
            bookViewModel.insertAuthorBook(AuthorBooks(tempAuthorId,it))
            tempBookId = it
        })

        bookViewModel.getAllAuthorBooks().observe(this, Observer {
            for(authorBook in it){
                Log.d("CUSTOM","authorId: ${authorBook.authorId}, bookId: ${authorBook.bookId}")
            }
        })

        btnAdd.setOnClickListener {
            bookViewModel.insertBook(Book(etTitle.text.toString()))
        }
    }


}
