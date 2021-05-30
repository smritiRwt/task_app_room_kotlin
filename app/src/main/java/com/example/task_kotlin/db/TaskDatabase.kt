package com.example.task_kotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.task_kotlin.db.dao.TaskDao
import com.example.task_kotlin.db.entity.Task
import java.security.AccessControlContext

@Database(entities = [Task::class],version = 1,exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract  fun getTaskDao():TaskDao

    companion object{
        @Volatile
         private  var instance: TaskDatabase?=null
        private  var LOCK=Any()

   operator  fun invoke (context: Context)= instance ?: synchronized(LOCK){
       instance ?: buildDatabase(context).also {
           instance=it
       }

   }

        private  fun buildDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            TaskDatabase::class.java,
            "taskdatabase"
        ).build()

    }

    }
