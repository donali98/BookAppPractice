package com.donali.bookapppractice


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.donali.bookapppractice.helpers.ActivityHelper
import com.donali.bookapppractice.viewmodels.BookViewModel


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val BOOK_INFO = "book_info"
private const val BOOK_AUTHORS = "book_authors"

class BookInfoFragment : Fragment() {

    lateinit var tvInfoTitle: TextView
    lateinit var activityHelper: ActivityHelper
    lateinit var bookViewModel: BookViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityHelper = context as ActivityHelper
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book_info, container, false)
        tvInfoTitle = view.findViewById(R.id.tv_info_title)
        tvInfoTitle.text = arguments!!.getString(BOOK_INFO, "sets")
        bookViewModel = activityHelper.getViewModel()
        tvInfoTitle.text = ""
        for (item in arguments!!.getStringArrayList(BOOK_AUTHORS)!!) {
            bookViewModel.getAuthorById(item.toLong()).observe(this, Observer {
                tvInfoTitle.text = "${tvInfoTitle.text},${it.name}"
            })
        }
        return view
    }

    companion object {


        fun newInstance(param1: String, param2: ArrayList<String>) =
                BookInfoFragment().apply {
                    arguments = Bundle().apply {
                        putString(BOOK_INFO, param1)
                        putStringArrayList(BOOK_AUTHORS, param2)
                    }
                }
    }


}
