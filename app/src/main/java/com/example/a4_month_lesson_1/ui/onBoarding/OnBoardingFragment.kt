package com.example.a4_month_lesson_1.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.a4_month_lesson_1.R
import com.example.a4_month_lesson_1.data.Pref
import com.example.a4_month_lesson_1.databinding.FragmentOnBoardingBinding
import com.example.a4_month_lesson_1.ui.onBoarding.adapter.OnBoardingAdapter

@Suppress("UNREACHABLE_CODE")
class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        val adapter = OnBoardingAdapter() {
            pref.saveSeen()
            findNavController().navigateUp()
        }
        binding.viewpager.adapter = adapter
        binding.indicator.setViewPager(binding.viewpager)
        adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver);
    }
        }


