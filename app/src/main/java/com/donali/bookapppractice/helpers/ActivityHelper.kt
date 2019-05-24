package com.donali.bookapppractice.helpers

import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.donali.bookapppractice.viewmodels.BookViewModel

interface ActivityHelper {
    fun getLayoutManager():RecyclerView.LayoutManager
    fun getCustomSupportFragmentMananager():FragmentManager
    fun getViewModel():BookViewModel
}