package com.donali.bookapppractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.donali.bookapppractice.helpers.ActivityHelper
import com.donali.bookapppractice.viewmodels.BookViewModel

class MainActivity : AppCompatActivity(),ActivityHelper {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bookFragment = BookFragment()

        supportFragmentManager.beginTransaction().add(R.id.fl_main_container,bookFragment).commit()
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager  = LinearLayoutManager(this)
    override fun getCustomSupportFragmentMananager(): FragmentManager  = supportFragmentManager
    override fun getViewModel(): BookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

}
