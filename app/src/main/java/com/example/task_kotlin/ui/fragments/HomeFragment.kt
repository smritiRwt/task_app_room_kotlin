package com.example.task_kotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.task_kotlin.R
import com.example.task_kotlin.db.TaskDatabase
import com.example.task_kotlin.ui.adapter.RecyclerViewAdpter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {
       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        //fetch to recyclerview
        launch{
            context?.let {
                val task=TaskDatabase(it).getTaskDao().getAllTasks()
                recyclerview.adapter=RecyclerViewAdpter(task)
            }
        }


        //naviagtion between fragments
        val fab: View = view.findViewById(R.id.addnew) as FloatingActionButton
        fab.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToAddTaskFragment()
            Navigation.findNavController(it).navigate(action)
        }

        }
}

