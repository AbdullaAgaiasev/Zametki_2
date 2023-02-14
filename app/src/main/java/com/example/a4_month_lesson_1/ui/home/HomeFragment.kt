package com.example.a4_month_lesson_1.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a4_month_lesson_1.App
import com.example.a4_month_lesson_1.R
import com.example.a4_month_lesson_1.databinding.FragmentHomeBinding
import com.example.a4_month_lesson_1.model.Task
import com.example.a4_month_lesson_1.ui.home.adapter.TaskAdapter
import com.example.a4_month_lesson_1.utils.isOnline
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: TaskAdapter
    private var db = Firebase.firestore
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::onLongClick, this::onClick)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireContext().isOnline()) {
            getTasks()
        } else {
            val tasks = App.db.taskDao().getAll()
            adapter.addTasks(tasks)
        }
        binding.recyclerView.adapter = adapter
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun getTasks() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            db.collection(uid).get().addOnSuccessListener {
                val data = it.toObjects(Task::class.java)
                adapter.addTasks(data)
            }.addOnFailureListener {
//                startFragment()
            }
        }
    }
    private fun onClick(task: Task) {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToTaskFragment(task))
    }

    private fun onLongClick(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Вы точно хотите избавиться от этого?")
        alertDialog.setNegativeButton("Нет", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.cancel()
            }
        })
        alertDialog.setPositiveButton("Да", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                App.db.taskDao().delete(task)
                val tasks = App.db.taskDao().getAll()
                adapter.addTasks(tasks)
                //                setData(
            }
        })
        alertDialog.create().show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


