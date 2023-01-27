package com.example.a4_month_lesson_1.ui.task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.test.core.app.ApplicationProvider
import com.example.a4_month_lesson_1.App
import com.example.a4_month_lesson_1.MainActivity
import com.example.a4_month_lesson_1.R
import com.example.a4_month_lesson_1.databinding.FragmentTaskBinding
import com.example.a4_month_lesson_1.model.Task


class TaskFragment : Fragment() {


    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
//            setFragmentResult(RESULT_TASK, bundleOf("task" to
//                    Task(title = binding.etTitle.text.toString(),
//                        desc = binding.etDesc.text.toString())
//            )
//            )
            App.db.taskDao().insert(Task(title = binding.etTitle.text.toString(),
                                         desc = binding.etDesc.text.toString()))
            findNavController().navigateUp()
        }
    }

//    fun setOnLongClickListener() {

//    }


//    companion object{
//        const val RESULT_TASK = "result.task"
//    }
}