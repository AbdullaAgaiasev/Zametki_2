package com.example.a4_month_lesson_1.ui.task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.test.core.app.ApplicationProvider
import com.example.a4_month_lesson_1.App
import com.example.a4_month_lesson_1.MainActivity
import com.example.a4_month_lesson_1.R
import com.example.a4_month_lesson_1.databinding.FragmentTaskBinding
import com.example.a4_month_lesson_1.model.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log


class TaskFragment : Fragment() {


    private lateinit var binding: FragmentTaskBinding
    private val db = Firebase.firestore
    private lateinit var navArg : TaskFragmentArgs
    private var task: Task? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            navArg = TaskFragmentArgs.fromBundle(it)
            task = navArg.task
        }
        if (task != null) {
            binding.edTitle.setText(task?.title)
            binding.edDesc.setText(task?.desc)
            binding.btnSave.text = getString(R.string.update)
        } else {
            binding.btnSave.text = getString(R.string.save)
        }

        binding.btnSave.setOnClickListener {
            if (task != null) {
                onUpdate()

            } else onSave()
        }
    }

    private fun onUpdate() {
        task?.title = binding.edTitle.text.toString()
        task?.desc = binding.edDesc.text.toString()
        task?.let { App.db.taskDao().update(it) }
        findNavController().navigateUp()
    }

    private fun onSave() {
        val task = Task(
            title = binding.edTitle.text.toString(),
            desc = binding.edDesc.text.toString()
        )
        putTask(task)
        App.db.taskDao().insert(task)
        findNavController().navigateUp()
    }

    private fun putTask(task: Task) {
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            db.collection(it).add(task).addOnSuccessListener {
                Log.e("ololo", "onSave: success!")
            }.addOnFailureListener {
                Log.e("ololo", "onSave: "+ it.message)
            }
        }
    }


//    companion object{
//        const val RESULT_TASK = "result.task"
//    }
}