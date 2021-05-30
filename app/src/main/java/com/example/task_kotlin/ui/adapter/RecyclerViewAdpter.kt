package com.example.task_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.task_kotlin.R
import com.example.task_kotlin.db.entity.Task
import com.example.task_kotlin.ui.fragments.HomeFragmentDirections
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerViewAdpter(private  val tasks:List<Task>) : RecyclerView.Adapter<RecyclerViewAdpter.TaskViewHolder>(){


    class  TaskViewHolder( view : View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
            return  TaskViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
            )
    }

    override fun getItemCount(): Int {
       return  tasks.size
            }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.itemView.tasktitle.text=tasks[position].title
        holder.itemView.description.text=tasks[position].note


        holder.itemView.setOnClickListener {view ->
            val action=HomeFragmentDirections.actionHomeFragmentToAddTaskFragment()
            action.task=tasks[position]
            Navigation.findNavController(view).navigate(action)
        }
    }
}