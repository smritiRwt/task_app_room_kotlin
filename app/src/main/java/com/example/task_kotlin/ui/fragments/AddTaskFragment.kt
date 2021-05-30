package com.example.task_kotlin.ui.fragments

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.AsyncTask.execute
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.task_kotlin.R
import com.example.task_kotlin.db.TaskDatabase
import com.example.task_kotlin.db.entity.Task
import com.example.task_kotlin.ui.toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class AddTaskFragment : BaseFragment() {

    var tasks:Task?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//update
        arguments?.let {
            tasks=AddTaskFragmentArgs.fromBundle(it).task
            title.setText(tasks?.title)
            task.setText(tasks?.note)

        }


        save.setOnClickListener {view ->
            val tt=title.text.toString().trim()
            val addTask=task.text.toString().trim()
            if(tt.isEmpty()){
                Toast.makeText(context, "Title required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(addTask.isEmpty()){
                Toast.makeText(context, "task required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            launch{
                context?.let {
                    val mtaskentity= Task(tt,addTask)
                        if(tasks==null)
                        {
                            TaskDatabase(it).getTaskDao().addTask(mtaskentity)//add
                            print(title.text)
                            print(title.text)
                            it.toast("task saved")
                        }
                            else
                        {
                            mtaskentity.id=tasks!!.id
                            TaskDatabase(it).getTaskDao().updateTask(mtaskentity)//update
                            print(title.text)
                            print(title.text)
                            it.toast("task updated")
                        }



                    val action=AddTaskFragmentDirections.actionAddTaskFragmentToHomeFragment()
                    Navigation.findNavController(view).navigate(action)
                }
            }


            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete ->if(tasks!=null) deleteTask() else context?.toast("cannot delete")
        }
        return super.onOptionsItemSelected(item)
    }

    //delete function
   private fun deleteTask()
    {
        AlertDialog.Builder(context).apply {
            setTitle("Are you delete this task?")
            setMessage("once its done,it cannot be reversed.")
            setPositiveButton("Yes"){_,_ ->
                launch {
                   TaskDatabase(context).getTaskDao().deleteTask(tasks!!)
                    val action=AddTaskFragmentDirections.actionAddTaskFragmentToHomeFragment()
                    Navigation.findNavController(view!!).navigate(action)
                }
            }
            setNegativeButton("No"){_,_ ->}
        }.create().show()
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }
}