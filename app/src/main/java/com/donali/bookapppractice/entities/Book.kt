package com.donali.bookapppractice.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book (
    @ColumnInfo(name = "title") val title:String
){
    @PrimaryKey(autoGenerate = true)  var id:Long = 0

}