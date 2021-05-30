package com.example.task_kotlin.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Task(


    @ColumnInfo(name = "task_title")
    val title:String,
    @ColumnInfo(name = "task_note")
    val note:String) :Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

