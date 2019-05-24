package com.donali.bookapppractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.donali.bookapppractice.entities.Book

class BookAdapter:RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    var books:List<Book> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_template,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        holder.bind(books[position])
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        lateinit var tvBookTitle:TextView

        fun bind(book:Book) = with(itemView){
            tvBookTitle = findViewById(R.id.tv_book_title)
            tvBookTitle.text = book.title
        }
    }
    /*
    Funcion que se ejecuta al momento en que ocurre un cambio en la tabla book
    (se agrega, modifica o elimina un registro), la cual actualiza el recycler
    con la nueva lista de libros
* */
    public fun setData(books:List<Book> ){
        this.books = books
        notifyDataSetChanged()
    }
}