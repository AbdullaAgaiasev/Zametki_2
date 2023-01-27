package com.example.a4_month_lesson_1.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.a4_month_lesson_1.App
import com.example.a4_month_lesson_1.R
import com.example.a4_month_lesson_1.databinding.FragmentHomeBinding
import com.example.a4_month_lesson_1.model.Task
import com.example.a4_month_lesson_1.ui.home.adapter.TaskAdapter
import com.example.a4_month_lesson_1.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
//    private val builder =  AlertDialog.Builder(requireContext())
    private lateinit var adapter: TaskAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::onLongClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()

//        setFragmentResultListener(TaskFragment.RESULT_TASK) { key, bundle ->
//            val result = bundle.getSerializable("task") as Task
//            adapter.addTask(result)
//        }


        binding?.recyclerView?.adapter = adapter
        binding?.fab?.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

    }

    private fun setData(){
        val tasks = App.db.taskDao().getAll()
        adapter.addTasks(tasks)
    }

    private fun onLongClick(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Do you want to delete")
        alertDialog.setNegativeButton("No", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.cancel()
            }
        })

        alertDialog.setPositiveButton("Yes", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                App.db.taskDao().delete(task)
                setData()
            }
        })
        alertDialog.create().show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

