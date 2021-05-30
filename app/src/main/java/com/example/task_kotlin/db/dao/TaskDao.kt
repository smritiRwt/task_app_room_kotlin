package com.example.task_kotlin.db.dao

import androidx.room.*
import com.example.task_kotlin.db.entity.Task

@Dao
interface  TaskDao{
    @Insert
    suspend fun addTask(task: Task)


    @Query("SELECT * FROM task ORDER BY id Desc")
    suspend fun getAllTasks():List<Task>

    @Insert
    suspend fun addMultipleTasks(vararg task:Task)

    @Update
    suspend fun updateTask(task:Task)

    @Delete
    suspend fun  deleteTask(task: Task)
}